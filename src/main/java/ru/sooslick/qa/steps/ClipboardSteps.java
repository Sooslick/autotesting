package ru.sooslick.qa.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.SneakyThrows;
import ru.sooslick.qa.core.assertions.StringVerifier;
import ru.sooslick.qa.core.repeaters.Repeat;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * Steps for various system clipboard interactions
 */
public class ClipboardSteps implements ClipboardOwner {

    @Given("A user copies text {dataGenerator} to clipboard")
    public void saveToClipboard(String content) {
        getClipboard().setContents(new StringSelection(content), this);
    }

    @Then("The clipboard has text {stringVerifier}")
    public void checkClipboardText(StringVerifier expectedText) {
        Repeat.untilSuccess(() -> {
            String actualText = getClipboardText();
            expectedText.test(actualText);
        });
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        // do nothing
    }

    public static Clipboard getClipboard() {
        return Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    @SneakyThrows
    public static String getClipboardText() {
        return getClipboard().getData(DataFlavor.stringFlavor).toString();
    }
}
