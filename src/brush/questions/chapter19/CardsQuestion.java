package brush.questions.chapter19;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: my_algorithm
 * @description 一张扑克有3个属性，每种属性有3种值（A、B、C）
 * * 比如"AAA"，第一个属性值A，第二个属性值A，第三个属性值A
 * * 比如"BCA"，第一个属性值B，第二个属性值C，第三个属性值A
 * * 给定一个字符串类型的数组cards[]，每一个字符串代表一张扑克
 * * 从中挑选三张扑克，一个属性达标的条件是：这个属性在三张扑克中全一样，或全不一样
 * * 挑选的三张扑克达标的要求是：每种属性都满足上面的条件
 * * 比如："ABC"、"CBC"、"BBC"
 * * 第一张第一个属性为"A"、第二张第一个属性为"C"、第三张第一个属性为"B"，全不一样
 * * 第一张第二个属性为"B"、第二张第二个属性为"B"、第三张第二个属性为"B"，全一样
 * * 第一张第三个属性为"C"、第二张第三个属性为"C"、第三张第三个属性为"C"，全一样
 * * 每种属性都满足在三张扑克中全一样，或全不一样，所以这三张扑克达标
 * * 返回在cards[]中任意挑选三张扑克，达标的方法数
 * @author: 34371
 * @create: 2022-06-16 11:38
 **/
public class CardsQuestion {

    public static void main(String[] args) {
        System.out.println(select(new String[]{
                "BBA", "CCB", "CBC", "BAC", "CCA", "ABA"
        }));
    }

    // ABC
    // 高位 -> 低位
    // A:0  B:1  C:2
    public static int select(String[] cards) {
        int[] lettersCounts = new int[27];
        Map<Integer, char[]> letterMap = new HashMap<>(27);
        for (int i = 0; i < cards.length; i++) {
            char[] letters = cards[i].toCharArray();
            int index = letter2Num(letters);
            if (!letterMap.containsKey(index)) {
                letterMap.put(index, cards[i].toCharArray());
            }
            lettersCounts[index]++;
        }

        //每个组统计自身组合 C n 3
        int sum = 0;
        for (int i = 0; i < lettersCounts.length; i++) {
            if (lettersCounts[i] >= 3) {
                sum += numberOfCombinations(lettersCounts[i]);
            }
        }

        //计算不同组的总组合
        for (int i = 0; i < lettersCounts.length; i++) {
            char[] card1 = letterMap.get(i);
            if (card1!=null){
                for (int j = i + 1; j < lettersCounts.length /*&& isDifferent(card1,letterMap.get(j))*/; j++) {
                    char[] card2 = letterMap.get(j);
                    if (card2!=null){
                        for (int k = j + 1; k < lettersCounts.length /*&& isDifferent(card1,letterMap.get(k)) && isDifferent(letterMap.get(j),letterMap.get(k))*/; k++) {
                            char[] card3 = letterMap.get(k);
                            if (card3!=null && isOK(card1, card2, card3))
                                sum += lettersCounts[i] * lettersCounts[j] * lettersCounts[k];
                        }
                    }
                }
            }
        }
        return sum;
    }

    public static boolean isOK(char[] c1, char[] c2, char[] c3) {
        boolean flag = true;
        for (int i = 0; i < 3; i++) {
            if (!((c1[i] == c2[i] && c2[i] == c3[i]) || (c1[i] != c2[i] && c1[i] != c3[i] && c3[i] != c2[i]))) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static boolean isDifferent(char[] c1, char[] c2) {
        if (c1 == null || c2 == null) {
            return false;
        }
        return c1[0] != c2[0] && c1[1] != c2[1] && c1[2] != c2[2];
    }

    public static int numberOfCombinations(int all) {
        return all * (all - 1) * (all - 2) / 6;
    }

    public static int letter2Num(char[] letters) {
        return (letters[0] - 'A') * 9 + (letters[1] - 'A') * 3 + (letters[2] - 'A');
    }

}
