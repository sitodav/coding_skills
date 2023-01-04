package sito.davide.utils;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class Messages
{

	public void logExAndDecorateErrorMessage(Exception ex, String message, Model mod, Logger logger)
	{
		if (null != ex)
			logger.error("Error", ex);

		mod.addAttribute(Params.ERROR_FLAG, true);
		mod.addAttribute(Params.ERROR_MESSAGE, message);

	}

	public void decorateSuccessMessage(String message, Model mod)
	{

		mod.addAttribute(Params.SUCCESS_FLAG, true);
		mod.addAttribute(Params.SUCCESS_MESSAGE, message);

	}

}
