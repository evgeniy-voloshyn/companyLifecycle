package CompanyLifecycle.util;

import CompanyLifecycle.Company.Company;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;


/**
 * Created by vitamin on 30.07.2015.
 */
public class ReportGenerator {

    private static final ReportGenerator INSTANCE = new ReportGenerator();

    private ReportGenerator(){}

    public static ReportGenerator getInstance() {
        return INSTANCE;
    }

    public void createReport(Company company){
        VelocityEngine engine = new VelocityEngine();
        engine.init();

        VelocityContext context = new VelocityContext();
        context.put("employees", company.getEmployees());
        context.put("freelancers", company.getFreelancers());
        context.put("positions", company.getPositions());

        context.put("numberOfTasks", company.getDirector().getNumberOfGivenTasks());

        String templatePath = "src/main/java/CompanyLifecycle/schema/report.vm";
        String outputPath = "src/main/java/CompanyLifecycle/report.txt";


        try (FileWriter fileWriter = new FileWriter(outputPath)) {
            Velocity.mergeTemplate(templatePath,"UTF-8", context, fileWriter);
        } catch (IOException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }

    }

}
