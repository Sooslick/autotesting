package ru.sooslick.qa.steps;

import io.cucumber.java.en.Given;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * Steps for various system clipboard interactions
 */
public class ClipboardSteps implements ClipboardOwner {

    @Given("A user copies text {dataGenerator} to clipboard")
    public void saveToClipboard(String content) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(content), this);
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        // do nothing
    }
}
