package sito.davide.utils;

import org.springframework.stereotype.Component;


@Component(value="Urls") 
public class Urls
{
	public static final String CSS_PATH = "/css/**";
	public static final String LOGIN_PATH = "/login";
	public static final String LOGINERROR_PATH = "/login_error";
	public static final String REGISTER_PATH = "/register";
	public static final String HOME_PATH = "/home";
	public static final String CAMBIANOTIFICA_PATH = "/cambiaNotifica";
}
