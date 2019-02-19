package pl.jj.util.html;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class read html file and map them to the HtmlModel objects.
 * @author JNartowicz
 */
public class HtmlReader {

    private static final String HTML_ID = "id";
    private static final String HTML_CLASS = "class";
    private static final String HTML_STYLE = "style";
    private static final String HTML_TEXT = "#text";

    private static final String SEMICOLON_SEPARATOR = ";";
    private static final String COLON_SEPARATOR = ":";
    private static final String SPACE_SEPARATOR = " ";
    private static final String NEXT_LINE = "\n";

    private String htmlFilePath;
    private File htmlFile;

    public HtmlReader(String htmlFilePath) {
        this.htmlFilePath = htmlFilePath;
        this.htmlFile = new File(this.htmlFilePath);
    }

    public HtmlReader(File htmlFile) {
        this.htmlFile = htmlFile;
    }

    /**
     * Read html file core method.
     * One tag must contain all children.
     * More than one parent is not supported.
     * @return - HtmlModel - parent model
     */
    public HtmlModel readFile() throws HtmlReaderException {

        try{

            //Check the html file exists
            if(htmlFile.exists()){

                //Initialize builder instances
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = builderFactory.newDocumentBuilder();

                //Xml document
                Document document = builder.parse(htmlFile);
                document.getDocumentElement().normalize();


                Node node = document.getDocumentElement();
                HtmlModel htmlModel = buildModelFromNode(node);

                //Execute construct html model method
                constructModelFromNode(htmlModel, node.getChildNodes());

                return htmlModel;

            } else {
                throw new HtmlReaderException(HtmlReaderException.FILE_NOT_EXISTS);
            }

        } catch (ParserConfigurationException e) {
            throw new HtmlReaderException(e);
        } catch (SAXException e) {
            throw new HtmlReaderException(e);
        } catch (IOException e) {
            throw new HtmlReaderException(e);
        }

    }


    /**
     * Method will add child nodes to the html model.
     * Everything will be converted to html mode.
     * @param parentHtmlModel - node container
     * @param childrenToAdd - nodes to convert
     */
    private void constructModelFromNode(HtmlModel parentHtmlModel, NodeList childrenToAdd) throws HtmlReaderException {

        if(childrenToAdd.getLength() == 0){
            return;
        }

        for(int i=0; i<childrenToAdd.getLength(); i++){

            //Get node children
            Node node = childrenToAdd.item(i);

            //Check is not a text
            if(node.getNodeName().equals(HTML_TEXT)){
                String text = node.getNodeValue();
                if(text != null && !text.isEmpty()){

                    //Trim read text
                    text = text.trim();

                    //Append to the html parent text
                    String parentText = parentHtmlModel.getText();
                    if(parentText == null){
                        parentText = "";
                    }

                    //Compose html parent text
                    String textBuilder = parentText.trim() + " " + text;
                    parentHtmlModel.setText(textBuilder.replace(NEXT_LINE, "").trim());

                }

            } else {

                //Html model from node
                HtmlModel htmlModel = buildModelFromNode(node);
                //Add html model to parameter model parent
                parentHtmlModel.addChild(htmlModel);

                //Recursively add every node
                constructModelFromNode(htmlModel, node.getChildNodes());

            }

        }

    }

    /**
     * Create a html model from node.
     * Method get data from the node and set them to the html model component.
     */
    private HtmlModel buildModelFromNode(Node node) throws HtmlReaderException {

        //Create an instance
        HtmlModel htmlModel = new HtmlModel();

        //Get the data
        String htmlTag = node.getNodeName(); //Set the node name
        htmlModel.setTag(htmlTag);

        //Performing attributes
        NamedNodeMap namedNodeMap = node.getAttributes();

        if(namedNodeMap != null){
            for(int i=0; i<namedNodeMap.getLength(); i++){

                Node attribute = namedNodeMap.item(i);

                String attributeName = attribute.getNodeName();
                String attributeValue = attribute.getNodeValue();

                if(attributeValue == null){
                    attributeValue = "";
                }

                //Get data from attributes
                if(attributeName != null){

                    //Value can be null, for example: disable
                    switch (attributeName){

                        case HTML_ID:
                            htmlModel.setId(attributeValue);
                            continue;
                        case HTML_CLASS:
                            for(String c: getClasses(attributeValue)){
                                htmlModel.addClass(c);
                            }
                            continue;
                        case HTML_STYLE:
                            Map<String, String> styleMap = getStyles(attributeValue);
                            htmlModel.setStyles(styleMap);
                            continue;
                        default:
                            htmlModel.addAttribute(attributeName, attributeValue);
                    }
                }
            }
        }

        return htmlModel;
    }

    /**
     * Convert css classes collection to string array.
      * @param c - string which contains class collection separated by space
     * @return - string array with classes names
     */
    private String[] getClasses(String c){
        return c.trim().split(SPACE_SEPARATOR);
    }

    /**
     * Get the style map.
     * @param attributeValue - correct style expression styleName: styleValue;
     * @return - style map
     * @throws HtmlReaderException - Array out of bound exception may be appear.
     */
    private Map<String, String> getStyles(String attributeValue) throws HtmlReaderException {

        try{

            //Create a style map
            Map<String, String> styleMap = new HashMap<>();

            //Split styles content by semicolon
            String[] styles = attributeValue.split(SEMICOLON_SEPARATOR);

            for(String style: styles){

                style = style.trim();

                if(!style.isEmpty()){

                    //Split style by colon
                    //It extract style name and value
                    String[] styler = style.split(COLON_SEPARATOR);

                    String styleName = styler[0].trim();
                    String styleValue = styler[1].trim();

                    //Add style to the map
                    styleMap.put(styleName, styleValue);

                }

            }

            return styleMap;

        } catch (Exception e){
            throw new HtmlReaderException(e);
        }

    }

}
