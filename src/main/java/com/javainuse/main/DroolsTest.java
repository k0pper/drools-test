package com.javainuse.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;
import org.drools.core.rule.Package;

import com.javainuse.model.Product;

public class DroolsTest {

	public static void main(String[] args) throws DroolsParserException,
			IOException {
		DroolsTest droolsTest = new DroolsTest();
		droolsTest.executeDrools();
		
		droolsTest.executeDrools2();
	}

	public void executeDrools() throws DroolsParserException, IOException {

		PackageBuilder packageBuilder = new PackageBuilder();

		String ruleFile = "/com/rule/Rules.drl";
		InputStream resourceAsStream = getClass().getResourceAsStream(ruleFile);
		
		InputStreamReader reader = new InputStreamReader(resourceAsStream);
		packageBuilder.addPackageFromDrl(reader);
		org.drools.core.rule.Package rulesPackage = packageBuilder.getPackage();
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		ruleBase.addPackage(rulesPackage);

		WorkingMemory workingMemory = ruleBase.newStatefulSession();

		Product product = new Product();
		product.setType("diamond");

		workingMemory.insert(product);
		workingMemory.fireAllRules();

		System.out.println("The discount for the product " + product.getType()
				+ " is " + product.getDiscount());
	}
	
	public void executeDrools2() throws DroolsParserException, IOException {
		PackageBuilder builder = new PackageBuilder();
		String ruleFile = "/com/rule/Rules.drl";
		InputStream resource = getClass().getResourceAsStream(ruleFile);
		InputStreamReader reader = new InputStreamReader(resource);
		builder.addPackageFromDrl(reader);
		Package rulesPackage = builder.getPackage();
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		ruleBase.addPackage(rulesPackage);
		WorkingMemory workingMemory = ruleBase.newStatefulSession();
		
		Product p = new Product();
		p.setType("gold");
		
		workingMemory.insert(p);
		workingMemory.fireAllRules();
		
		System.out.println("The discount for the product " + p.getType() + " is " + p.getDiscount());
	}
}
