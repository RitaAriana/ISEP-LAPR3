package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Company;

import java.io.IOException;
import java.sql.SQLException;

public class FindContainerSituationController {

    private final Company company;

    public FindContainerSituationController(){
        this.company= App.getInstance().getCompany();

    }

    public FindContainerSituationController(Company company){
        this.company=company;

    }

    public String getContainerLocation(Integer containerId) throws SQLException, IOException {

        US204Handler handler= new US204Handler();

        return handler.getContainerLocation(containerId);
    }
}
