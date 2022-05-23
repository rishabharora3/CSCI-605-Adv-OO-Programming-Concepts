package week4.q2;

/*
 * Pattern.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */
public class Pattern {
    private static final String[] patterns = new String[]{"ˆab$", ".a+b.", ".ab.", "ˆ[ab]c$", "ˆ(bab|aba)$", "ˆ..\\1\\2$", "ˆ..\\2\\1$"};

    public static String matchPattern(String word) {
        char[] array = word.toCharArray();

        if (pattern1(array))
            return patterns[0];
        else if (pattern3(array))
            return patterns[2];
        else if (pattern4(array))
            return patterns[3];
        else if (pattern5(array))
            return patterns[4];
        else if (pattern6(array))
            return patterns[5];
        else if (pattern7(array))
            return patterns[6];

        return " No pattern exist for";
    }

    private static boolean pattern1(char[] word) {
        boolean flag = false;
        int currentState = 0;
        for (int i = 0; i < word.length && currentState != 3; i++) {
            switch (currentState) {
                case 0: {
                    switch (word[i]) {
                        case 'a': {
                            currentState = 1;
                            break;
                        }
                        default: {
                            currentState = 3;
                        }
                    }
                }
                break;
                case 1: {
                    switch (word[i]) {
                        case 'b': {
                            currentState = 2;
                            break;
                        }
                        default: {
                            currentState = 3;
                            break;
                        }
                    }
                }
                break;
                case 2: {
                    switch (word[i]) {
                        default: {
                            currentState = 3;
                            break;
                        }
                    }
                }
                case 3: {
                    switch (word[i]) {
                        default: {
                            currentState = 3;
                            break;
                        }
                    }
                }
                break;
                default: {
                    System.out.println("how did we get here? #pattern 1");
                }
            }
        }
        if (currentState == 2) {
            flag = true;
        }
        return flag;

    }

    private static boolean pattern2(char[] word) {
        boolean flag = false;
        int currentState = 0;

        for (int i = 0; i < word.length && currentState != 2; i++) {
            switch (currentState) {
                case 0: {
                    switch (word[i]) {
                        case 'a': {
                            currentState = 1;
                            break;
                        }
                        default: {
                            // currentState = currentState; //Stay in same state
                            break;
                        }
                    }
                }
                break;
                case 1: {
                    switch (word[i]) {
                        case 'b': {
                            currentState = 2;
                            break;
                        }
                        case 'a': {
                            // currentState = currentState; // stay in same state
                            break;
                        }
                        default: {
                            currentState = 0;
                            break;
                        }
                    }
                }
                break;

                case 2: {
                    switch (word[i]) {
                        default:
                            // currentState = currentState;//stay accepted
                            break;
                    }
                }
                break;
                default: {
                    System.out.println("how did we get here pattern 2");
                }
            }
        }

        if (currentState == 2) {
            flag = true;
        }

        return flag;
    }

    private static boolean pattern3(char[] word) {
        if (word.length < 4)
            return false;
        int state = 0;
        int errorState = 5;
        int finalState = 4;
        for (int index = 0; (state != errorState) && (index < word.length); index++) {
            if (state == 0) {
                if (index != word.length - 1 && word[index] != ' ' && word[index + 1] == 'a') {
                    state = 1;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == 1) {
                if (word[index] == 'a') {
                    state = 2;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == 2) {
                if (word[index] == 'b') {
                    state = 3;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == 3) {
                if (word[index] != ' ') {
                    state = finalState;
                    break;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            }
        }
        return state == finalState;
    }

    private static boolean pattern4(char[] word) {
        if (word.length < 2)
            return false;
        int state = 0;
        int errorState = 3;
        int finalState = 2;
        for (int index = 0; (state != errorState) && (index < word.length); index++) {
            if (state == 0) {
                if (word[index] == ' ') {
                    state = 0;
                } else if (index == 0 && (word[index] == 'a' || word[index] == 'b')) {
                    state = 1;
                } else if (index > 0 && word[index - 1] == ' ' && (word[index] == 'a' || word[index] == 'b')) {
                    state = 1;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == 1) {
                if (word[index] == 'c') {
                    state = finalState;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == finalState && index != word.length - 1) {
                if (word[index] == ' ') {
                    break;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            }
        }
        return state == finalState;
    }

    private static boolean pattern5(char[] word) {
        if (word.length < 3)
            return false;
        int state = 0;
        int errorState = 6;
        int finalState = 3;
        for (int index = 0; (state != errorState) && (index < word.length); index++) {
            if (state == 0) {
                if (word[index] == ' ') {
                    state = 0;
                } else if (index == 0 && word[index] == 'a') {
                    state = 1;
                } else if (index > 0 && word[index - 1] == ' ' && word[index] == 'a') {
                    state = 1;
                } else if (index == 0 && word[index] == 'b') {
                    state = 4;
                } else if (index > 0 && word[index - 1] == ' ' && word[index] == 'b') {
                    state = 4;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == 1) {
                if (word[index] == 'b') {
                    state = 2;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == 4) {
                if (word[index] == 'a') {
                    state = 5;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == 2) {
                if (word[index] == 'a') {
                    state = finalState;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == 5) {
                if (word[index] == 'b') {
                    state = finalState;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == finalState && index != word.length - 1) {
                if (word[index] == ' ') {
                    break;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            }
        }
        return state == finalState;
    }

    private static boolean pattern6(char[] word) {
        if (word.length < 4)
            return false;
        int state = 0;
        int errorState = 4;
        int finalState = 3;
        for (int index = 0; (state != errorState) && (index < word.length); index++) {
            if (state == 0) {
                if (word[index] == ' ') {
                    state = 0;
                } else if (index == 0 && word[index] != word[index + 1]) {
                    state = 1;
                } else if (index > 0 && word[index - 1] == ' ' && word[index] != word[index + 1]) {
                    state = 1;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == 1) {
                if (word[index + 1] == word[index - 1]) {
                    state = 2;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == 2) {
                if (word[index + 1] == word[index - 1]) {
                    state = finalState;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == finalState && index != word.length - 1) {
                if (word[index + 1] == ' ') {
                    break;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            }
        }
        return state == finalState;
    }

    private static boolean pattern7(char[] word) {
        if (word.length < 4)
            return false;
        int state = 0;
        int errorState = 4;
        int finalState = 3;
        for (int index = 0; (state != errorState) && (index < word.length); index++) {
            if (state == 0) {
                if (word[index] == ' ') {
                    state = 0;
                } else if (index == 0 && word[index] != word[index + 1]) {
                    state = 1;
                } else if (index > 0 && word[index - 1] == ' ' && word[index] != word[index + 1]) {
                    state = 1;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == 1) {
                if (word[index] == word[index + 1]) {
                    state = 2;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == 2) {
                if (word[index + 1] == word[index - 2]) {
                    state = finalState;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            } else if (state == finalState && index != word.length - 1) {
                if (word[index + 1] == ' ') {
                    break;
                } else
                    state = index == word.length - 1 ? errorState : 0;
            }

        }
        return state == finalState;
    }

}
