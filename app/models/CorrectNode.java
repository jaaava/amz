package models;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * User: tarvo
 * Date: 3/22/13
 * Time: 3:15 PM
 */
public class CorrectNode {
    public String name = null;
    public String[] parents = null;
    private Node node = null;

    public CorrectNode(String name, String... parents) {
        this.name = name;
        this.parents = parents;
    }

    public void checkNode(NodeList tagElement) {
        for (int i = 0; i < tagElement.getLength(); i++) {
            parentsMatched((this.node = tagElement.item(i)), 0);
        }
    }

    private boolean parentsMatched(Node node, int index) {
        for (int i = index; i < parents.length; i++) {
            if (node.getParentNode().getNodeName().equals(parents[i])) {
                node = node.getParentNode();
                parentsMatched(node, i + 1); // CAUTION: recursion
                return true;
            }
        }
        return false;
    }

    public Node getNode() {
        return this.node;
    }
}
