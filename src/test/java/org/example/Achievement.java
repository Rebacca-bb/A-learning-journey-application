package org.example;

/*
 * @author：Qinding Zhang，Jieran Zhang
 * */
public class Achievement {
    private String mathContent;
    private String englishContent;
    private String projectContent;
    private String scienceContent;

    public String getProjectContent() {
        return this.projectContent;
    }

    public String getScienceContent() {
        return this.scienceContent;
    }

    public String getEnglishContent() {
        return this.englishContent;
    }

    public String getMathContent() {
        return this.mathContent;
    }

    public void setMathContent(String text) {
        this.mathContent=text;
    }

    public void setEnglishContent(String text) {
        this.englishContent=text;
    }

    public void setProjectContent(String text) {
        this.projectContent=text;
    }

    public void setScienceContent(String text) {
        this.scienceContent=text;
    }

    // getter 和 setter 方法
}
