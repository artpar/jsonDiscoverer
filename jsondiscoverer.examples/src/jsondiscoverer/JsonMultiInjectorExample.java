/*******************************************************************************
 * Copyright (c) 2008, 2013
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Javier Canovas (me@jlcanovas.es) 
 *******************************************************************************/

package jsondiscoverer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import jsondiscoverer.JsonMultiInjector;
import jsondiscoverer.SingleJsonSource;
import jsondiscoverer.coverage.Coverage;
import jsondiscoverer.coverage.util.CoverageCreator;

public class JsonMultiInjectorExample {

	public static String INPUT_FILE1 = "./json/tan2.json";
	public static String METAMODEL_FILE1 = "./json/tan2.ecore";
	public static String COVERAGE_FILE1 = "./json/tan2.coverage.xmi";

	public static String INPUT_FILE2 = "./json/group/tan1A.json";
	public static String METAMODEL_FILE2 = "./json/group/tan1.ecore";
	public static String COVERAGE_FILE2 = "./json/group/tan1.coverage.xmi";

	public static String COMPOSITE_OUTPUT_FILE = "tan-composed.xmi";


	public static void main(String[] args) throws FileNotFoundException {
		SingleJsonSource source1 = new SingleJsonSource("source1");
		source1.addJsonData(null, new FileReader(new File(INPUT_FILE1)));
		
		Coverage coverage1 = CoverageCreator.loadCoverage(new File(COVERAGE_FILE1));

		SingleJsonSource source2 = new SingleJsonSource("source2");
		source2.addJsonData(null, new FileReader(new File(INPUT_FILE2)));

		Coverage coverage2 = CoverageCreator.loadCoverage(new File(COVERAGE_FILE2));
		
		JsonMultiInjector injector = new JsonMultiInjector(source1, coverage1, source2, coverage2);
		injector.multiInject(new File(COMPOSITE_OUTPUT_FILE));
	}

}