package sito.davide.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sito.davide.dto.UserDTO;
import sito.davide.service.UserService;
import sito.davide.utils.Messages;
import sito.davide.utils.Urls;

@Controller
public class MainController
{
	
	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@Autowired
	Messages messages;
	
	@Autowired
	UserService userService;
	
	/*Semplice metodo di routing */
	@GetMapping(value=Urls.LOGIN_PATH)
	public String loginPage(Model model,HttpServletRequest request)
	{
		try
		{	 
			
			if(request.getSession() != null && request.getSession().getAttribute("user") != null)
			{
				//loggato gia'
				return "home";
			}
		}
		catch(Exception ex)
		{
			messages.logExAndDecorateErrorMessage(ex, "Unexpected error", model, log);
		}
		
		return "login" ; 
	}
	
	/*
	 * Non abbiamo bisogno di mappare la post sulla login in quanto se ne occupa spring security
	 * avendo configurato l'indirizzo di auth su /login (va in post il mapping di spring security)
	 */
	
	/*Semplice metodo di routing */
	@GetMapping(value=Urls.LOGINERROR_PATH)
	public String loginError(Model model,HttpServletRequest request)
	{
		try
		{
			if(request.getSession() != null && request.getSession().getAttribute("user") != null)
			{
				//loggato gia'
				return "home";
			}
			
			 messages.logExAndDecorateErrorMessage(null, "Username/password/OTP errati", model, log);
		}
		catch(Exception ex)
		{
			messages.logExAndDecorateErrorMessage(ex, "Unexpected error", model, log);
		}
		
		return "login";
	}
	
	/*Semplice metodo di routing */
	@GetMapping(value=Urls.REGISTER_PATH)
	public String registerPage(Model model, HttpServletRequest request)
	{
		try
		{
			if(request.getSession() != null && request.getSession().getAttribute("user") != null)
			{
				//loggato gia'
				return "home";
			}
			
			model.addAttribute("user",new UserDTO());
		}
		catch(Exception ex)
		{
			messages.logExAndDecorateErrorMessage(ex, "Unexpected error", model, log);
		}
		
		return "register";
	}
	
	
	@PostMapping(value=Urls.REGISTER_PATH)
	public String saveNewUser(
				@ModelAttribute("user") @Valid UserDTO user,
                BindingResult bRes ,
                Model model)
	{
		try
		{
			 UserDTO savedUser = userService.createUser(user, bRes);
			 if(null == savedUser) //qui il binding result farà si che i campi rifiutati faranno apparire nel form gli errori
			 {
				 model.addAttribute("user",user);
			 }
			 else
			 {
				 model.addAttribute("user", new UserDTO()); //per pulire i campi
				 if(savedUser.isTwoFactAuth())
					 model.addAttribute("otpQR",savedUser.getOtpQR());
				 
				 model.addAttribute("isTwoFactAuth",savedUser.isTwoFactAuth());
				 
				 messages.decorateSuccessMessage("Utente creato con successo", model);
			 }
			
		}
		catch(Exception ex)
		{
			messages.logExAndDecorateErrorMessage(ex, "Unexpected error", model, log);
		}
		
		return "register";
	}
	
	/*Simple route method */
	@GetMapping(value=Urls.HOME_PATH)
	public String homePage(Model model, HttpServletRequest servletRequest)
	{
		try
		{
			 String loggedUsername = ((UserDTO) servletRequest.getSession().getAttribute("user")).getUsername();
			//creiamo il model sotto per poter usare il form con cui modificare l e notifiche nella home page
			//e lo valorizziamo con i dati dell'utente (quelli che mostriamo, quelli relativi alle notifiche)
			UserDTO userData = userService.getUserByUsername(loggedUsername);
			model.addAttribute("user",userData);
		}
		catch(Exception ex)
		{
			messages.logExAndDecorateErrorMessage(ex, "Unexpected error", model, log);
		}
		
		return "home";
	}
	
	@PostMapping(value=Urls.CAMBIANOTIFICA_PATH)
	public String cambiaNotifica(
				@ModelAttribute("user")  UserDTO user,
                Model model,
                HttpServletRequest servletRequest)
				
	{
		try
		{
			/*L'utente lo prendiamo dalla sessione */
			
			 String loggedUsername = ((UserDTO) servletRequest.getSession().getAttribute("user")).getUsername();
			 user.setUsername(loggedUsername);
			 userService.updateUserNotifyMethod(user);
			 
			 
			 /*bisogna aggiornare l'utente in sessione */
			 servletRequest.getSession().setAttribute("user",userService.getUserByUsername(loggedUsername));
			 
			 /*ed il model del form */
			 UserDTO userData = userService.getUserByUsername(loggedUsername);
			 model.addAttribute("user",userData);
			 
			 messages.decorateSuccessMessage("Operazione eseguita con successo", model);
			
		}
		catch(Exception ex)
		{
			messages.logExAndDecorateErrorMessage(ex, "Unexpected error", model, log);
		}
		
		return "home";
	}
	
	
	
}
