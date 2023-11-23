package leetcode.brushQuestions.hard;

/**
 * @author gusixue
 * @description 2296. 设计一个文本编辑器
 * @date 2023/7/23
 */
public class TextEditor {

    StringBuffer textBuffer;

    int curIndex;

    public TextEditor() {
        textBuffer = new StringBuffer();
        curIndex = 0;
    }

    public void addText(String text) {
        textBuffer.insert(curIndex, text);
        curIndex += text.length();
    }

    public int deleteText(int k) {
        int prevLen = textBuffer.length();

        int start = Math.max(0, curIndex - k);
        textBuffer.delete(start, curIndex);
        curIndex = start;

        return prevLen - textBuffer.length();
    }

    public String cursorLeft(int k) {
        curIndex = Math.max(0, curIndex - k);
        return textBuffer.substring(Math.max(0, curIndex - 10), curIndex);
    }

    public String cursorRight(int k) {
        curIndex = Math.min(textBuffer.length(), curIndex + k);
        return textBuffer.substring(Math.max(0, curIndex - 10), curIndex);
    }

    public static void main(String[] args) {
        TextEditor t = new TextEditor();
        System.out.println(t.curIndex + " : " + t.textBuffer);

        t.addText("123");
        System.out.println(t.curIndex + " : " + t.textBuffer.toString());

        int d1 = t.deleteText(10);
        System.out.println(t.curIndex + " : " + t.textBuffer.toString() + " : " + d1);

        t.addText("456");
        System.out.println(t.curIndex + " : " + t.textBuffer.toString());

        t.addText("789");
        System.out.println(t.curIndex + " : " + t.textBuffer.toString());

        String l1 = t.cursorLeft(2);
        System.out.println(t.curIndex + " : " + t.textBuffer.toString() + " : " + l1);

        t.addText("123");
        System.out.println(t.curIndex + " : " + t.textBuffer.toString());

        int d2 = t.deleteText(1);
        System.out.println(t.curIndex + " : " + t.textBuffer.toString() + " : " + d2);

        String r1 = t.cursorRight(2);
        System.out.println(t.curIndex + " : " + t.textBuffer.toString() + " : " + r1);


    }

}
