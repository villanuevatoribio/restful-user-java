package com.test.application.user.util.constans;
public final class Constants {

    private Constants() {

    }

    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String EMAIL_CONFLICT_MESSAGE = "El correo ya registrado.";
    public static final String INVALID_EMAIL_MESSAGE = "El email no tiene el formato correcto: "
            + "<username>@<dominio>.cl";
    public static final String INVALID_PASSWORD_MESSAGE = "El password no tiene el formato "
            + "correcto: Al menos una letra mayuscula, una letra minuscula, un numero, un "
            + "caracter especial (@, $, !, %, ?, &, ., -, _), minimo ocho y maximo 20 caracteres";
    public static final String INVALID_AUTHENTICATION_MESSAGE = "Autenticacion invalida.";
    public static final String FORBIDDEN_MESSAGE = "Acceso no permitido";

    public static final String PREFIX_ROLE_AUTHORITY = "ROLE_";
    public static final String PREFIX_BEARER = "Bearer ";
    public static final String JWT_CLAIM_ROLE = "role";
    public static final String ROLE_USER = "USER";

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";

    public static final String EMPTY_SPACE = "";

    public static final String AUTH_URI = "/api/v1/auth";
    public static final String H2_CONSOLE_URI = "/h2-console/**";
    public static final String SWAGGER_UI_URI = "/swagger-ui/**";
    public static final String V3_URI = "/v3/**";

}

