package utility

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import userPackage.UserData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable

public class Utility {
	@Keyword
	def runBatch(String path) {
		def cmd = "cmd /c \"java -jar \"" + path + "\"\"";
		runCmd(cmd)
	}

	def runCmd(String cmd) {
		KeywordUtil.logInfo("cmd: ${cmd}")

		def proc = cmd.execute();
		def outputStream = new StringBuffer();
		def errStream = new StringBuffer()
		proc.waitForProcessOutput(outputStream, errStream);
		println(outputStream.toString());
		println(errStream.toString())

		if(proc.exitValue() != 0){
			KeywordUtil.markFailed("Out:" + outputStream.toString() + ", Err: " + errStream.toString())
		}
	}

	@Keyword
	def Unit() {
		userPackage.Main userdata = new userPackage.Main();
		userdata.setFirstName("FromKatalon");
		userdata.setUserData("something", "lanme", 35, 99282882)
		println (userdata.firstName);
		println(userdata.CalculateInterest());
	}

	@Keyword
	def int setUserData(String fName, String lName, int age, int phoneNumber ) {
		userPackage.Main userdata = new userPackage.Main();
		userdata.setUserData(fName,lName, age, phoneNumber);
		println(userdata.CalculateInterest());
		return userdata.CalculateInterest();
	}
}
