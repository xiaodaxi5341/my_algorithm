package brush.questions.chapter20;

/**
 * @program: my_algorithm
 * @description 如果只给定一个二叉树前序遍历数组pre和中序遍历数组in，能否不重建树，而直接生成这个二叉树的后序数组并返回，已知二叉树中没有重复值
 * @author: 34371
 * @create: 2022-06-16 12:55
 **/

/**
 * 当二叉树以以下方式转为数组后：
 * 先序遍历，头节点一定在最开始
 * 后续遍历，头节点一定在最后位置
 * 中序遍历：头节点的左边是左子树，头节点右边是右子树
 */
public class PreAndInToPost {

    public static int[] getPostArrByPreAndIn(int[] pre, int[] in) {
        if (pre == null) {
            return null;
        }
        if (pre.length == 0) {
            return new int[0];
        }

        int[] post = new int[pre.length];
        getPost(pre, 0, pre.length - 1, in, 0, in.length - 1, post, 0, post.length - 1);
        return post;
    }

    private static void getPost(int[] pre, int preLeft, int preRight,
                                int[] in, int inLeft, int inRight,
                                int[] post, int postLeft, int postRight) {
        if (preLeft > preRight) {
            return;
        }
        if (preRight == preLeft) {
            post[postLeft] = pre[preLeft];
            return;
        }
        post[postRight] = pre[preLeft];
        int i = inLeft;
        for (; i <= inRight; i++) {
            if (pre[preLeft] == in[i]) {
                break;
            }
        }
        int indexSurplus = i - 1 - inLeft;
        getPost(pre, preLeft + 1, preLeft + 1 + indexSurplus, in, inLeft, i - 1, post, postLeft, postLeft + indexSurplus);
        getPost(pre, preLeft + 2 + indexSurplus, preRight, in, i + 1, inRight, post, postLeft + indexSurplus + 1, postRight - 1);
    }

}
