/**********************************************************************************************
 * Copyright 2009 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file 
 * except in compliance with the License. A copy of the License is located at
 *
 *       http://aws.amazon.com/apache2.0/
 *
 * or in the "LICENSE.txt" file accompanying this file. This file is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License. 
 *
 * ********************************************************************************************
 *
 *  Amazon Product Advertising API
 *  Signed Requests Sample Code
 *
 *  API Version: 2009-03-31
 *
 */

package models;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * This class shows how to make a simple authenticated ItemLookup call to the
 * Amazon Product Advertising API.
 * 
 * See the README.html that came with this sample for instructions on
 * configuring and running the sample.
 */
public class ItemLookup {
    /*
     * Your AWS Access Key ID, as taken from the AWS Your Account page.
     */
    private static final String AWS_ACCESS_KEY_ID = "AKIAJ5YEMSZSX2SXW7GA";
    /*
     * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
     * Your Account page.
     */
    private static final String AWS_SECRET_KEY = "h/E7Hqs7rKW+PDtbR1EJfd0067GtWwKaOSM75tWN";
    /*
     * Use one of the following end-points, according to the region you are
     * interested in:
     * 
     *      US: ecs.amazonaws.com 
     *      CA: ecs.amazonaws.ca 
     *      UK: ecs.amazonaws.co.uk 
     *      DE: ecs.amazonaws.de 
     *      FR: ecs.amazonaws.fr 
     *      JP: ecs.amazonaws.jp
     * 
     */
    private static final String ENDPOINT = "ecs.amazonaws.com";

    public static List<String> find(String find, Integer page, String category, NodeHelper.ElementType enumElementType) {
        /*
         * Set up the signed requests helper 
         */
        SignedRequestsHelper helper;
        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        /*
         * Here is an example in map form, where the request parameters are stored in a map.
         */

        Map<String, String> params = new HashMap<String, String>();

        params.put("Service", "AWSECommerceService");
        params.put("AssociateTag", "wwwblogcom0fa-20");
        params.put("Version", "2009-03-31");
        params.put("Operation", "ItemSearch");
        params.put("Keywords", find);
        params.put("SearchIndex", category);
        params.put("ItemPage", page.toString());
        params.put("ResponseGroup", "Medium");


        String requestUrl = helper.sign(params);

        List<String> response = fetchRequest(requestUrl, enumElementType.elementName, enumElementType.elementParents);
        return response;

    }
    /*
     * Utility function to fetch the response from the service and extract the
     * title from the XML.
     */

    private static List<String> fetchRequest(String requestUrl, String findFor, String[] parents) {
        System.out.println("------- REQUESTING "+ findFor +" ----------");
        List<String> response = new ArrayList<String>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(requestUrl);

            NodeHelper.xmlElementIndex = 0; // start parsing XML from n-th element

            for (int i = 0; i < doc.getElementsByTagName("Item").getLength(); i++) {
                NodeList tagElement = doc.getElementsByTagName(findFor);
                Node node = new NodeHelper(parents).checkNode(tagElement).getNodeInstance();

                String nodeText = node.getTextContent();
                response.add(nodeText);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
