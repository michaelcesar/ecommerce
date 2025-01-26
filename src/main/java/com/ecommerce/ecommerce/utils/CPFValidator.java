package com.ecommerce.ecommerce.utils;

public class CPFValidator {

    public static boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += (cpf.charAt(i) - '0') * (10 - i);
            }
            int checkDigit1 = 11 - (sum % 11);
            if (checkDigit1 >= 10) checkDigit1 = 0;

            if (checkDigit1 != (cpf.charAt(9) - '0')) {
                return false;
            }

            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += (cpf.charAt(i) - '0') * (11 - i);
            }
            int checkDigit2 = 11 - (sum % 11);
            if (checkDigit2 >= 10) checkDigit2 = 0;

            return checkDigit2 == (cpf.charAt(10) - '0');
        } catch (Exception e) {
            return false;
        }
    }
}
