package io.demo.jony.jony.core.api;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Iterables;

/**
 * Domain with all API Versions available.
 * 
 * @author Virtus
 *
 */
public enum ApiVersions {

	V1("1");

	/**
	 * All version as list.
	 */
	protected static final List<ApiVersions> VERSIONS = Arrays.asList(values());
	
	/**
	 * Latest version available.
	 */
	public static final ApiVersions LASTEST = Iterables.getLast(VERSIONS);
	
	/**
	 * Version number.
	 */
	private String version;

	/**
	 * Constructor.
	 * 
	 * @param version
	 * 		Version number.
	 */
	private ApiVersions(String version) {
		this.version = version;
	}

	/**
	 * Gets the version number.
	 * 
	 * @return Version number.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Gets the range with all versions between 
	 * 
	 * @param range
	 * 		Range with the first and last version.
	 * @return List of all versions available between the range specified. 
	 */
	public static List<ApiVersions> between(ApiVersions[] range) {
		int index = 0;
		
		ApiVersions begin = range[index++];
		ApiVersions end = range.length > 1 ? range[index] : LASTEST;
		
		return VERSIONS.subList(VERSIONS.indexOf(begin), VERSIONS.indexOf(end) + 1);
	}
}
