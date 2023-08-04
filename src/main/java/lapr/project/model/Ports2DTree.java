package lapr.project.model;

import lapr.project.model.esinf.KDTree;

import java.util.List;


/**
 * Class that represents a Ports2DTree
 *
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class Ports2DTree extends KDTree<Ports> {

    public Ports2DTree(){
        super();
    }

    public Ports2DTree(List<Node<Ports>> lst){
        super(lst);
    }


}
