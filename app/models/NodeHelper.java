package models;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * User: tarvo
 * Date: 3/22/13
 * Time: 3:15 PM
 */
public final class NodeHelper {
    static int xmlElementIndex = 0;
    private String[] parents = null;
    private Node node = null;

    public NodeHelper(String[] parents) {
        this.parents = parents;
    }

    public NodeHelper checkNode(NodeList tagElement) {
        for (int i = xmlElementIndex; i < tagElement.getLength(); i++) {
            xmlElementIndex++;
            boolean matchFound = parentsMatched(tagElement.item(i));
            if (matchFound) {
                this.node = tagElement.item(i);
                break;
            }
        }
        return this;
    }

    private boolean parentsMatched(Node node) {
        for (int i = 0; i < this.parents.length; i++) {
            Node nodesParent = node.getParentNode();
            if (nodesParent.getNodeName().equals(this.parents[i])) {
                node = nodesParent;
                continue;
            }
            return false;
        }
        return true;
    }

    public Node getNodeInstance() {
        return this.node;
    }

    public enum ElementType {
        TITLES("Title", new String[]{"ItemAttributes", "Item"}),
        IMAGES("URL", new String[]{"SmallImage", "ImageSet", "ImageSets", "Item"});
        public final String[] elementParents;
        public final String elementName;

        private ElementType(String elementName, String[] elementParents) {
            this.elementName = elementName;
            this.elementParents = elementParents;
        }
    }
}
