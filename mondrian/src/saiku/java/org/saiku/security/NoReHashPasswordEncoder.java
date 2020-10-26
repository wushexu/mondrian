package org.saiku.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

public class NoReHashPasswordEncoder implements PasswordEncoder {
    private PasswordEncoder encoder;
    private Pattern pattern;

    public NoReHashPasswordEncoder() {
        this(new BCryptPasswordEncoder(), "\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
    }

    public NoReHashPasswordEncoder(PasswordEncoder encoder, String pattern) {
        this.encoder = encoder;
        this.pattern = Pattern.compile(pattern);
    }

    @Override
    public String encode(CharSequence charSequence) {
        if (isHash(charSequence)) {
            return charSequence.toString();
        }
        return encoder.encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encoder.matches(charSequence, s);
    }

    private boolean isHash(CharSequence charSequence) {
        return pattern.matcher(charSequence).matches();
    }
}
