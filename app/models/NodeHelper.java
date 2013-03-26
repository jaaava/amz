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
    public NodeHelper(){

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
        IMAGES("Image", new String[]{"SmallImage", "Item"}), //"ImageSet", "ImageSets"
        ASINS("ASIN", new String[]{"Item"}); //"ImageSet", "ImageSets"

        public final String[] elementParents;
        public final String elementName;

        private ElementType(String elementName, String[] elementParents) {
            this.elementName = elementName;
            this.elementParents = elementParents;
        }
    }

    public void find(Node itemInFocus, String findFor){
        NodeList nodeList = itemInFocus.getChildNodes(); // Item nodes

        for (int i = 0; i < nodeList.getLength(); i++) { // Item nodes length
            //System.out.print(nodeList.item(i));
            Node itemNode = nodeList.item(i);
            System.out.print("itemNode: " + itemNode);
            System.out.println(" --- ASIN:" + itemNode.getFirstChild());
        }
        System.out.println();
        System.out.println("-------");
    }
}
