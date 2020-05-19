package com.servicenow.ExtentReportListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.servicenow.util.Constants;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

public class ExtentReportListener implements IReporter {
	ExtentReports report;
	public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1,

            String outputDirectory) {

		report = new ExtentReports();
		report.attachReporter(new ExtentHtmlReporter(Constants.EXTENT_REPORT_PATH));
		
        // Second parameter of this method ISuite will contain all the suite executed.
        for (ISuite iSuite : arg1) {

         //Get a map of result of a single suite at a time
            Map<String,ISuiteResult> results =    iSuite.getResults();

         //Get the key of the result map
            Set<String> keys = results.keySet();

        //Go to each map value one by one
            for (String key : keys) {

             //The Context object of current result
            ITestContext context = results.get(key).getTestContext();
            
            buildTestNodes(context.getPassedTests(), Status.PASS);
            buildTestNodes(context.getFailedTests(), Status.FAIL);
            buildTestNodes(context.getSkippedTests(), Status.SKIP);


            }

        }
        
        report.flush();
        
    }
	
	void buildTestNodes(IResultMap tests, Status status)
	{
		ExtentTest test;
		if(tests.size() > 0)
		{
			for(ITestResult result : tests.getAllResults()) {
				test = report.createTest(result.getMethod().getMethodName());
				
				for(String group : result.getMethod().getGroups())
				{
					test.assignCategory(group);
				}
				
				if(result.getThrowable() != null)
				{
					test.log(status, result.getThrowable());
					
				}
				else
				{
					test.log(status, "Test " +status.toString().toLowerCase()+"ed");
				}
				report.flush();
			}
		}
		
	}
}
