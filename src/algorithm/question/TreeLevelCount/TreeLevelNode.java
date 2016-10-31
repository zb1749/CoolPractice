package algorithm.question.TreeLevelCount;

/**
 * Created by Kevin on 2016/10/31.
 */

import java.util.LinkedList;
import java.util.List;


/**
 * 树节点定义，每个节点可以有多个子节点
 */
class TreeLevelNode {
    // 节点值
    private String value;
    // 子节点列表，为了简单，直接用LinkedList
    // 当然，你也可以自定义一个TreeLevelNode链表
    private List<TreeLevelNode> subNodeList = new LinkedList<TreeLevelNode>();

    public TreeLevelNode() {

    }

    public TreeLevelNode(String value) {
        this.value = value;
    }

    public TreeLevelNode(String value, List<TreeLevelNode> subNodeList) {
        this.value = value;
        this.subNodeList = subNodeList;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<TreeLevelNode> getSubNodeList() {
        return subNodeList;
    }

    public void setSubNodeList(List<TreeLevelNode> subNodeList) {
        this.subNodeList = subNodeList;
    }

    public void addSub(TreeLevelNode node) {
        this.subNodeList.add(node);
    }

    public void addSub(TreeLevelNode[] nodes) {
        for (TreeLevelNode node : nodes) {
            this.subNodeList.add(node);
        }
    }
}


