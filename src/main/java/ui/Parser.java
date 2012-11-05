package ui;

import util.DirectionUtil;

import java.util.HashSet;
import java.util.Scanner;

import static com.google.common.collect.Sets.newHashSet;

public class Parser {
    private static final HashSet<String> NO_SET = newHashSet("n", "no");
    private static final HashSet<String> YES_SET = newHashSet("y", "yes");
    private static final HashSet<String> HELP_SET = newHashSet("h", "help");
    private static final HashSet<String> DANCE_SET = newHashSet("dance");
    private static final HashSet<String> LOOK_SET = newHashSet("l", "look");
    private static final HashSet<String> INV_SET = newHashSet("inv", "inventory");
    private static final HashSet<String> PROBE_SET = newHashSet("p", "probe");
    private static final HashSet<String> UNLOCK_SET = newHashSet("unlock door");
    private final Scanner scanner;
    private String prompt;

    public Parser(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean checkBegin() {
        System.out.print("> ");
        prompt = scanner.nextLine();
        verifyPromptNotNull();
        prompt.toLowerCase();
        while (!YES_SET.contains(prompt) && !NO_SET.contains(prompt)) {
            System.out.println("Please reply with yes or no.");
            System.out.print("> ");
            prompt = scanner.nextLine();
        }
        if (YES_SET.contains(prompt)) {
            return true;
        }
        return false;
    }

    public void processNextPrompt() {
        System.out.print("> ");
        prompt = scanner.nextLine();
        verifyPromptNotNull();
        prompt.toLowerCase();
    }

    public String getPrompt() {
        return prompt;
    }

    private void verifyPromptNotNull() {
        while (prompt == null) {
            System.out.print("> ");
            prompt = scanner.nextLine();
        }
    }

    public boolean isDance() {
        return DANCE_SET.contains(prompt);
    }

    public boolean isUnlock() {
        return UNLOCK_SET.contains(prompt);
    }

    public boolean isProbe() {
        Scanner promptScanner = new Scanner(prompt);
        if (promptScanner.hasNext()) {
            String action = promptScanner.next();
            return (PROBE_SET.contains(action) && promptScanner.hasNext());
        } else {
            return false;
        }
    }

    public boolean isInventory() {
        return INV_SET.contains(prompt);
    }

    public boolean isLook() {
        return LOOK_SET.contains(prompt);
    }

    public boolean isHelp() {
        return HELP_SET.contains(prompt);
    }

    public boolean isDirection() {
        return DirectionUtil.isDirection(prompt);
    }

    public boolean isGameOver() {
        return prompt.equals("qq");
    }
}
