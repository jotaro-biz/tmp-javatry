/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.colorbox;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.ObjDoubleConsumer;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.javatry.colorbox.base.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author jotaro.yuza
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        ColorBox colorBox = colorBoxList.get(0);
        BoxColor boxColor = colorBox.getColor();
        String colorName = boxColor.getColorName();
        int answer = colorName.length();
        log(answer, colorName); // also show name for visual check
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String maxStr = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = (String) content;
                    int currentLength = strContent.length();
                    if (maxStr == null || maxStr.length() < currentLength) {
                        maxStr = strContent;
                    }
                }
            }
        }
        log(maxStr != null ? maxStr : "Not found string content");
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int maxLength = 0;
        int minLength = 1000000000;
        int diffLength;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = (String) content;
                    int currentLength = strContent.length();
                    if (maxLength == 0 || maxLength < currentLength) {
                        maxLength = currentLength;
                    }
                    if (minLength == 1000000 || minLength > currentLength) {
                        minLength = currentLength;
                    }
                }
            }
        }
        diffLength = maxLength - minLength;
        log(maxLength >= minLength ? diffLength : "Not found string content");
    }

    /**
     * Which value (toString() if non-string) has second-max legnth in color-boxes? (without sort)<br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (ソートなしで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        // Collections.reverse(colorBoxList);
        String maxStr = null;
        String secondMaxStr = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content != null) {
                    String strContent = content.toString(); // 文字列以外はtoString()
                    if (maxStr == null || maxStr.length() < strContent.length()) {
                        secondMaxStr = maxStr;
                        maxStr = strContent;
                    } else if (secondMaxStr == null || secondMaxStr.length() < strContent.length()) {
                        secondMaxStr = strContent;
                    }
                }

            }
        }
        log(secondMaxStr != null ? secondMaxStr : "Not found string content");
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int SumLength = 0; // 何もなかったらnullではなく0
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = (String) content;
                    int currentLength = strContent.length();
                    SumLength += currentLength;
                }
            }
        }
        log(SumLength != 0 ? SumLength : "There is no string contents");
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        String maxStrColor = null;
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            if (maxStrColor == null || maxStrColor.length() < colorName.length()) { // 同率の場合は最初に出てきた人
                maxStrColor = colorName;
            }
        }
        log(maxStrColor != null ? maxStrColor : "Not found string content");
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String>startingWordList = new ArrayList<>();
        String searchWord = "Water";

        outerloop: for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            String colorName = colorBox.getColor().getColorName();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String && ((String)content).startsWith(searchWord)) {
                    startingWordList.add(colorName);
                    break outerloop;
                }
            }
        }
        if (!startingWordList.isEmpty()){
            for (String startingWord : startingWordList) {
                log(startingWord);
            }
        } else {
            log("There is no color in the color-box that has string starting with {}", searchWord);
        }
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String colorName = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = (String) content;
                    if (strContent.endsWith("front")) {
                        BoxColor boxColor = colorBox.getColor();
                        colorName = boxColor.getColorName();
                    }
                }
            }
        }
        log(colorName != null ? colorName : "There is no color in the color-box that has string ending with \"front\"");
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with "front" of string ending with "front" in color-boxes? <br>
     * (あなたのカラーボックスに入ってる "front" で終わる文字列で、"front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int startingChar = 0;
        String searchWord = "front";
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = (String) content;
                    if (strContent.endsWith(searchWord)) {
                        startingChar = strContent.lastIndexOf(searchWord) + 1; // 最後の一致
                    }
                }
            }
        }
        log(startingChar != 0 ? startingChar : "There is no color in the color-box that has string ending with {}", searchWord);
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (あなたのカラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String targetChar = "ど";
        int count;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                count = 0;
                if (content instanceof String) {
                    String strContent = (String) content;
                    String[] strArray = strContent.split("");
                    for (int i = 0; i < strArray.length; i++) {
                        if (strArray[i].equals(targetChar)) {
                            count++;
                            if (count >= 2) {
//                                System.out.println(strArray[i -1]);
//                                System.out.println(i);
//                                System.out.println(strContent);
                                log(strContent  + ':' + (i + 1));
                            }
                        }
                    }
                }
            }
        }
    }
    // indexOf != lastIndexOf で求められる

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String searchWord = "front";
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = (String) content;
                    if (strContent.endsWith(searchWord)) {
                        log(strContent.substring(0,1));
                        log(strContent);
                    }
                }
            }
        }
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String searchWord = "Water";
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = (String) content;
                    if (strContent.startsWith(searchWord)) {
                        log(strContent.substring(strContent.length() - 1));
                        log(strContent);
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String searchWord = "o";
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String strContent = (String) content;
                    if (strContent.contains(searchWord)) {
                        String replacedContent =  strContent.replace(searchWord, "");
                        log(replacedContent.length());
                        log("{}->{}",strContent,replacedContent);
                    }
                }
            }
        }
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_file_separator() { // TODO jojo もう一度解く (2019/04/25)
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if(content instanceof File) {
                    String filePath = ((File) content).getPath();
                    log(filePath);
                    log(filePath.replace("/", "\\"));
                }
            }
        }
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        List<ColorBox> colorBoxList =  new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if(content instanceof YourPrivateRoom.DevilBox) {
                    log(((String)content).length());
                }
            }
        }
//        log(colorBoxList);

//        YourPrivateRoom.DevilBox devilBox = new YourPrivateRoom.DevilBox("");
//        devilBox.wakeUp();
//        devilBox.allowMe();
//        devilBox.open();
//        log(devilBox.getText());

    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
    }
}
