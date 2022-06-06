package brush.questions.chapter10;

import java.util.*;

/**
 * @program: my_algorithm
 * @description
 * @author: 34371
 * @create: 2022-06-02 19:41
 **/

public class TopK {

    public static void main(String[] args) {
        TopK topK = new TopK(3);
        topK.add("yes");
        topK.add("lint");
        topK.add("code");
        topK.add("yes");
        topK.add("code");
        topK.add("baby");
        topK.add("you");
        topK.add("baby");
        topK.add("chrome");
//        topK.topk();
        System.out.println(Arrays.toString(topK.topk().toArray()));

    }

    Info[] heap;
    int size;
    //字符串在小根堆中的下标索引
    //如果map里没有对应的字符串或者下标为-1，表示不在小根堆中
    Map<Info, Integer> string2Index;
    //key：字符串，value：已经添加过的字符串的信息
    Map<String, Info> stringInfoMap;
    //比较器
    Comparator<Info> comparator;

    public TopK(int k) {
        heap = new Info[k];
        string2Index = new HashMap<>(k);
        stringInfoMap = new HashMap<>(k);
        size = 0;
        comparator = new InfoComparator();
    }

    public void add(String word) {
        if (this.heap.length == 0) {
            return;
        }
        if (string2Index.containsKey(stringInfoMap.get(word)) /*&& string2Index.get(word) != -1*/) {
            Integer wordIndex = string2Index.get(stringInfoMap.get(word));
            if (wordIndex != -1) {
                //已经在topK里了
                heap[wordIndex].times++;
                heapify(wordIndex);
            } else {
                //不在topK里
                Info exInfo = stringInfoMap.get(word);
                exInfo.times++;
                if (comparator.compare(exInfo, heap[0]) > 0) {
                    string2Index.put(exInfo, 0);
                    string2Index.put(heap[0], -1);
                    heap[0] = exInfo;
                    heapify(0);
                }
            }
        } else {

            Info newInfo = new Info(word, 1);
            stringInfoMap.put(word, newInfo);
            string2Index.put(newInfo, -1);

            if (size < heap.length ) {
                //堆里没有满
                heap[size] = newInfo;
                //调整堆
                string2Index.put(newInfo, size);
                heapInsert(size++);
            }else if (comparator.compare(newInfo,heap[0])>0){
                string2Index.put(heap[0],-1);
                heap[0] = newInfo;
                string2Index.put(newInfo,0);
                heapify(0);
            }
        }
    }

    public List<String> topk() {
        List<String> result = new ArrayList<>();
        TreeMap<Info, Integer> sortMap = new TreeMap<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o2.times - o1.times == 0 ? o1.word.compareTo(o2.word) : o2.times - o1.times;
            }
        });
        if (this.heap.length != 0 && size != 0) {
            for (int i = 0; i < size; i++) {
                sortMap.put(heap[i], 0);
            }
            while (!sortMap.isEmpty()) {
                Map.Entry<Info, Integer> entry = sortMap.pollFirstEntry();
                result.add(entry.getKey().word);
            }
        }
        return result;
    }

    private void heapify(int index) {
        Info toCompare;
        while ((toCompare = getLittle(index)) != null && comparator.compare(heap[index], toCompare) > 0) {
            Integer toSwapIndex = string2Index.get(toCompare);
            swap(toSwapIndex, index);
            index = toSwapIndex;
        }
    }

    private Info getLittle(int index) {
        Info left = ((index << 1) + 1) < size ? heap[(index << 1) + 1] : null;
        Info right = ((index << 1) + 2) < size ? heap[(index << 1) + 2] : null;

        Info toCompare = null;
        if (left == null) {
            toCompare = right;
        } else if (right == null) {
            toCompare = left;
        } else {
            toCompare = comparator.compare(left, right) < 0 ? left : right;
        }
        return toCompare;
    }

    private void heapInsert(int index) {
        //添加到这个位置，然后上浮，与其父比较看谁小
        while (comparator.compare(heap[index], heap[(index - 1) / 2]) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
//        return index;
    }

    private void swap(int subIndex, int parentIndex) {
        Integer subIndexInMap = string2Index.get(heap[subIndex]);
        Integer parentIndexInMap = string2Index.get(heap[parentIndex]);
        string2Index.put(heap[subIndex], parentIndexInMap);
        string2Index.put(heap[parentIndex], subIndexInMap);
        Info temp = heap[subIndex];
        heap[subIndex] = heap[parentIndex];
        heap[parentIndex] = temp;
    }


    class Info {
        String word;
        int times;

        Info(String word, int times) {
            this.word = word;
            this.times = times;
        }
    }

    class InfoComparator implements Comparator<Info> {
        @Override
        public int compare(Info o1, Info o2) {
            return o1.times - o2.times == 0 ? o2.word.compareTo(o1.word) : o1.times - o2.times;
        }
    }

}

