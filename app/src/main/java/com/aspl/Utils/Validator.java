package com.aspl.Utils;

import android.text.TextUtils;
import com.aspl.mbsmodel.CardType;
import java.util.ArrayList;
import java.util.List;

public class Validator {


    public static final byte VISA = 0;
    public static final byte MASTERCARD = 1;
    public static final byte AMEX = 2;
    public static final byte DINERS_CLUB = 3;
    public static final byte CARTE_BLANCHE = 4;
    public static final byte DISCOVER = 5;
    public static final byte ENROUTE = 6;
    public static final byte JCB = 7;


    private static String cardType = "", retType;

    public static boolean validateCard(String card) {

        card = card.replace(" ", "");
        if (card.length() == 13) {
            if (card.substring(0, 1).equals("4")) {
                cardType = "visa";
                return checkCreditCard(card, cardType);
            }else{
                return false;
            }
        } else if (card.length() == 16) {
            if (card.substring(0, 1).equals("6")) {
                cardType = "discover";
                return checkCreditCard(card, cardType);
            } else if (card.substring(0, 1).equals("5")) {
                cardType = "master";
                return checkCreditCard(card, cardType);
            } else if (card.substring(0, 1).equals("4")) {
                cardType = "visa";
                return checkCreditCard(card, cardType);
            } else {
                return false;
            }
        } else if (card.length() == 15) {
            if (card.substring(0, 1).equals("3")) {
                cardType = "amex";
                return checkCreditCard(card, cardType);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkCreditCard(String cardNumber, String  cardName){
        boolean isCardValid = true;

        List<CardType> liCardType = new ArrayList<>();

        CardType cardTypes = new CardType();
        cardTypes.setName("");
        cardTypes.setLength("");
        cardTypes.setPrefixes("");
        cardTypes.setCheckDigit(true);
        liCardType.add(cardTypes);

        CardType cardType1 = new CardType();
        cardType1.setName("visa");
        cardType1.setLength("13,16");
        cardType1.setPrefixes("4");
        cardType1.setCheckDigit(true);
        liCardType.add(cardType1);

        CardType cardType2 = new CardType();
        cardType2.setName("master");
        cardType2.setLength("16");
        cardType2.setPrefixes("51,52,53,54,55");
        cardType2.setCheckDigit(true);
        liCardType.add(cardType2);

        CardType cardType3 = new CardType();
        cardType3.setName("diners");
        cardType3.setLength("14");
        cardType3.setPrefixes("300,301,302,303,304,305,36,38");
        cardType3.setCheckDigit(true);
        liCardType.add(cardType3);

        CardType cardType4 = new CardType();
        cardType4.setName("carte");
        cardType4.setLength("14");
        cardType4.setPrefixes("300,301,302,303,304,305,36,38");
        cardType4.setCheckDigit(true);
        liCardType.add(cardType4);

        CardType cardType5 = new CardType();
        cardType5.setName("amex");
        cardType5.setLength("15");
        cardType5.setPrefixes("34,37");
        cardType5.setCheckDigit(true);
        liCardType.add(cardType5);

        CardType cardType6 = new CardType();
        cardType6.setName("discover");
        cardType6.setLength("16");
        cardType6.setPrefixes("6011");
        cardType6.setCheckDigit(true);
        liCardType.add(cardType6);

        CardType cardType7 = new CardType();
        cardType7.setName("jcb");
        cardType7.setLength("16,16");
        cardType7.setPrefixes("3,1800,2131");
        cardType7.setCheckDigit(true);
        liCardType.add(cardType7);

        CardType cardType8 = new CardType();
        cardType8.setName("enroute");
        cardType8.setLength("15");
        cardType8.setPrefixes("2014,2149");
        cardType8.setCheckDigit(true);
        liCardType.add(cardType8);

        CardType cardType9 = new CardType();
        cardType9.setName("switch");
        cardType9.setLength("16,18,19");
        cardType9.setPrefixes("4,5,6");
        cardType9.setCheckDigit(false);
        liCardType.add(cardType9);

        CardType cardType10 = new CardType();
        cardType10.setName("solo");
        cardType10.setLength("16,18,19");
        cardType10.setPrefixes("63, 6767");
        cardType10.setCheckDigit(false);
        liCardType.add(cardType10);

        int tempCardType = -1;
        for (int i = 0; i < liCardType.size(); i++) {
            if (cardName.toLowerCase().equals(liCardType.get(i).getName().toLowerCase())){
                tempCardType = i;
                break;
            }
        }

        if (tempCardType == -1){
            isCardValid = false;
        }

        if (cardNumber.length() == 0){
            isCardValid = false;
        }

        String tempCardNumber = "";
        tempCardNumber = cardNumber;

        /*String expire = "/^([0-9]{4})([0-9]{4})([0-9]{4})([0-9]{1,8})$/";
        Pattern pattern = Pattern.compile("/^([0-9]{4})([0-9]{4})([0-9]{4})([0-9]{1,8})$/");
        Matcher matcher = pattern.matcher(tempCardNumber);
        if (!matcher.matches()){
            Toast.makeText(MainActivity.getInstance(), "Invalid Pattern", Toast.LENGTH_SHORT).show();
            return false;
        }*/

        if (liCardType.get(tempCardType).getCheckDigit()){
            int checksum = 0;
            int j = 1;
            int calc = 0;

            for (int i = tempCardNumber.length() - 1 ; i >= 0 ; i--) {
                int carAt = Character.digit(tempCardNumber.charAt(i), 10);
                calc = carAt * j;
                if (calc > 9){
                    checksum = checksum + 1;
                    calc = calc - 10;
                }
                checksum = checksum + calc;
                if (j == 1){
                    j = 2;
                }else {
                    j = 1;
                }
            }

            if ((checksum % 10) != 0){
                isCardValid = false;
            }
        }

        boolean LengthValid = false;
        boolean PrefixValid = false;
        String[] prefix ;
        String[] lengths ;

        prefix = liCardType.get(tempCardType).getPrefixes().split(",");
        for (int i = 0; i < prefix.length; i++) {
            String exp = prefix[i];
            int length = exp.length();
            if (exp.equals(tempCardNumber.substring(0,length))){
                PrefixValid = true;
            }
        }
        if (!PrefixValid){
            isCardValid = false;
        }

        lengths = liCardType.get(tempCardType).getLength().split(",");
        for (int i = 0; i < lengths.length; i++) {
            if (tempCardNumber.length() == Integer.parseInt(lengths[i])){
                LengthValid = true;
            }
        }

        if (!LengthValid){
            isCardValid = false;
        }

        return isCardValid;
    }

    public static boolean validate(final String credCardNumber, final byte type) {
        String creditCard = credCardNumber.trim();
        boolean applyAlgo = false;
        switch (type) {
            case VISA:
                // VISA credit cards has length 13 - 15
                // VISA credit cards starts with prefix 4
                if (creditCard.length() >= 13 && creditCard.length() <= 16
                        && creditCard.startsWith("4")) {
                    applyAlgo = true;
                }
                break;
            case MASTERCARD:
                // MASTERCARD has length 16
                // MASTER card starts with 51, 52, 53, 54 or 55
                if (creditCard.length() == 16) {
                    int prefix = Integer.parseInt(creditCard.substring(0, 2));
                    if (prefix >= 51 && prefix <= 55) {
                        applyAlgo = true;
                    }
                }
                break;
            case AMEX:
                // AMEX has length 15
                // AMEX has prefix 34 or 37
                if (creditCard.length() == 15
                        && (creditCard.startsWith("34") || creditCard
                        .startsWith("37"))) {
                    applyAlgo = true;
                }
                break;
            case DINERS_CLUB:
            case CARTE_BLANCHE:
                // DINERSCLUB or CARTEBLANCHE has length 14
                // DINERSCLUB or CARTEBLANCHE has prefix 300, 301, 302, 303, 304,
                // 305 36 or 38
                if (creditCard.length() == 14) {
                    int prefix = Integer.parseInt(creditCard.substring(0, 3));
                    if ((prefix >= 300 && prefix <= 305)
                            || creditCard.startsWith("36")
                            || creditCard.startsWith("38")) {
                        applyAlgo = true;
                    }
                }
                break;
            case DISCOVER:
                // DISCOVER card has length of 16
                // DISCOVER card starts with 6011
                if (creditCard.length() == 16 && creditCard.startsWith("6011")) {
                    applyAlgo = true;
                }
                break;
            case ENROUTE:
                // ENROUTE card has length of 16
                // ENROUTE card starts with 2014 or 2149
                if (creditCard.length() == 16
                        && (creditCard.startsWith("2014") || creditCard
                        .startsWith("2149"))) {
                    applyAlgo = true;
                }
                break;
            case JCB:
                // JCB card has length of 16 or 15
                // JCB card with length 16 starts with 3
                // JCB card with length 15 starts with 2131 or 1800
                if ((creditCard.length() == 16 && creditCard.startsWith("3"))
                        || (creditCard.length() == 15 && (creditCard
                        .startsWith("2131") || creditCard
                        .startsWith("1800")))) {
                    applyAlgo = true;
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
        if (applyAlgo) {
            return validate(creditCard);
        }
        return false;
    }

    public static boolean validate(String creditCard) {
        // 4 9 9 2 7 3 9 8 7 1 6
        // 6
        // 1 x 2 = 2  = (0 + 2) = 2
        // 7
        // 8 x 2 = 16 = (1 + 6) = 7
        // 9
        // 3 x 2 = 6 = (0 + 6) = 6
        // 7
        // 2 x 2 = 4 = (0 + 4) = 4
        // 9
        // 9 X 2 = 18 = (1 + 8) = 9
        // 4
        // 6+2+7+7+9+6+7+4+9+9+4 = 70
        // return 0 == (70 % 10)
        int sum = 0;
        int length = creditCard.length();
        for (int i = 0; i < creditCard.length(); i++) {
            if (0 == (i % 2)) {
                sum += creditCard.charAt(length - i - 1) - '0';
            } else {
                sum += sumDigits((creditCard.charAt(length - i - 1) - '0') * 2);
            }
        }
        return 0 == (sum % 10);
    }

    private static int sumDigits(int i) {
        return (i % 10) + (i / 10);
    }


    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}