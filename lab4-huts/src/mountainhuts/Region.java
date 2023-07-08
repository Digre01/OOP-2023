package mountainhuts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;




/**
 * Class {@code Region} represents the main facade
 * class for the mountains hut system.
 * 
 * It allows defining and retrieving information about
 * municipalities and mountain huts.
 *
 */
public class Region {
	private String name;
	private List<String> ranges = new LinkedList<>();
	private List<Municipality> municipalities = new LinkedList<>();
	private List<MountainHut> mountainHuts = new LinkedList<>();
	

	//suus
	/**
	 * Create a region with the given name.
	 * 
	 * @param name
	 *            the name of the region
	 */
	public Region(String name) {
		this.name = name;
	}

	/**
	 * Return the name of the region.
	 * 
	 * @return the name of the region
	 */
	public String getName() {
		return name;
	}

	/**
	 * Create the ranges given their textual representation in the format
	 * "[minValue]-[maxValue]".
	 * 
	 * @param ranges
	 *            an array of textual ranges
	 */
	public void setAltitudeRanges(String... ranges) {
		this.ranges = Arrays.stream(ranges).collect(Collectors.toList());

	}

	/**
	 * Return the textual representation in the format "[minValue]-[maxValue]" of
	 * the range including the given altitude or return the default range "0-INF".
	 * 
	 * @param altitude
	 *            the geographical altitude
	 * @return a string representing the range
	 */
	public String getAltitudeRange(Integer altitude) {
		
		Optional<String> validRange = ranges.stream().filter(s->checkInRange(s,altitude)).findFirst();
		
		if(!validRange.isEmpty())
			return validRange.get().toString();
		
		return "0-INF";
	}
	
	public boolean checkInRange(String range, Integer altitude) {
		String[] l = range.split("-");
		Integer down = Integer.parseInt(l[0].replace("[", "").replace("]", ""));
		Integer top = Integer.parseInt(l[1].replace("[", "").replace("]", ""));
		
		//System.out.println(top + " " + down + "-->" + altitude + " cmp1:" + cmp1 + " cmp2: "+cmp2 );
		
		
		if(down < altitude && top >= altitude)
			return true;
		
		
		
		return false;
	}

	/**
	 * Return all the municipalities available.
	 * 
	 * The returned collection is unmodifiable
	 * 
	 * @return a collection of municipalities
	 */
	public Collection<Municipality> getMunicipalities() {
		return municipalities;
	}

	/**
	 * Return all the mountain huts available.
	 * 
	 * The returned collection is unmodifiable
	 * 
	 * @return a collection of mountain huts
	 */
	public Collection<MountainHut> getMountainHuts() {
		return mountainHuts;
	}

	/**
	 * Create a new municipality if it is not already available or find it.
	 * Duplicates must be detected by comparing the municipality names.
	 * 
	 * @param name
	 *            the municipality name
	 * @param province
	 *            the municipality province
	 * @param altitude
	 *            the municipality altitude
	 * @return the municipality
	 */
	public Municipality createOrGetMunicipality(String name, String province, Integer altitude) {
		
		Municipality mun = municipalities.stream().filter(x -> x.getName().equals(name))
				.findFirst().orElseGet(() -> {
					Municipality m = new Municipality(name,province,altitude);
					municipalities.add(m);
					return m;
				});
			
		
		
		return mun;
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 *
	 * @param name
	 *            the mountain hut name
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return the mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, String category, Integer bedsNumber,
			Municipality municipality) {
		
		
		MountainHut moun = mountainHuts.stream().filter(x -> x.getName().equals(name))
				.findFirst().orElseGet(() -> {
					MountainHut m = new MountainHut(name,null,category,bedsNumber,municipality);
					mountainHuts.add(m);
					return m;
				});
		
		return moun;
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 * 
	 * @param name
	 *            the mountain hut name
	 * @param altitude
	 *            the mountain hut altitude
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return a mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, Integer altitude, String category, Integer bedsNumber,
			Municipality municipality) {
		
		
		MountainHut moun = mountainHuts.stream().filter(x -> x.getName().equals(name))
				.findFirst().orElseGet(() -> {
					MountainHut m = new MountainHut(name,altitude,category,bedsNumber,municipality);
					mountainHuts.add(m);
					return m;
				});
		
		return moun;
		
		
	}

	/**
	 * Creates a new region and loads its data from a file.
	 * 
	 * The file must be a CSV file and it must contain the following fields:
	 * <ul>
	 * <li>{@code "Province"},
	 * <li>{@code "Municipality"},
	 * <li>{@code "MunicipalityAltitude"},
	 * <li>{@code "Name"},
	 * <li>{@code "Altitude"},
	 * <li>{@code "Category"},
	 * <li>{@code "BedsNumber"}
	 * </ul>
	 * 
	 * The fields are separated by a semicolon (';'). The field {@code "Altitude"}
	 * may be empty.
	 * 
	 * @param name
	 *            the name of the region
	 * @param file
	 *            the path of the file
	 */
	public static Region fromFile(String name, String file) {
		Region r = new Region(name);
		List<String> fileList = readData(file); //contains lines
		
		fileList.remove(0); //remove first line
		
		fileList.forEach(line -> {
			//clean the file
			String[] cells = line.split(";");
			String province = cells[0];
			String municipalityName = cells[1];
			Integer municipalityAltitude = Integer.parseInt(cells[2]);
			
			Municipality m = r.createOrGetMunicipality(municipalityName, province, municipalityAltitude);
			
			String HuntName = cells[3];
			String HuntAltitude = cells[4];
			String category = cells[5];
			Integer bedsNumber = Integer.parseInt(cells[6]);
			
			if(HuntAltitude.equals("")) {
				r.createOrGetMountainHut(HuntName, category, bedsNumber, m);
			}
			else {
				r.createOrGetMountainHut(HuntName,Integer.parseInt(HuntAltitude), category, bedsNumber, m);
			}
		});
		
		return r;
	}

	/**
	 * Reads the lines of a text file.
	 *
	 * @param file path of the file
	 * @return a list with one element per line
	 */
	public static List<String> readData(String file) {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().collect(Collectors.toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ArrayList<>();
		}
	}

	/**
	 * Count the number of municipalities with at least a mountain hut per each
	 * province.
	 * 
	 * @return a map with the province as key and the number of municipalities as
	 *         value
	 */
	public Map<String, Long> countMunicipalitiesPerProvince() {
		Map<String, Long> map;
		
	   map = municipalities.stream().collect(Collectors.groupingBy(Municipality :: getProvince,Collectors.counting()));
		
		
		
		
		return map;
	}

	/**
	 * Count the number of mountain huts per each municipality within each province.
	 * 
	 * @return a map with the province as key and, as value, a map with the
	 *         municipality as key and the number of mountain huts as value
	 */
	public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
		
		Map<String, Map<String, Long>> map;
		map = mountainHuts.stream().collect(Collectors.groupingBy(x -> x.getMunicipality().getProvince(),
				Collectors.groupingBy( y -> y.getMunicipality().getName(), 
						Collectors.counting()
						)
				));
		
		return map;
	}

	/**
	 * Count the number of mountain huts per altitude range. If the altitude of the
	 * mountain hut is not available, use the altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the number of mountain huts
	 *         as value
	 */
	public Map<String, Long> countMountainHutsPerAltitudeRange() {
		Map<String, Long> map;
		
		map = mountainHuts.stream().collect(Collectors.groupingBy(
				x -> getAltitudeRange(x),
				Collectors.counting()
				
				));
		
		
		return map;
	}
	
	private String getAltitudeRange(MountainHut h) {
		if(h.getAltitude().isPresent()) {
			return getAltitudeRange(h.getAltitude().get());
		}
		return getAltitudeRange(h.getMunicipality().getAltitude());
	}
	/**
	 * Compute the total number of beds available in the mountain huts per each
	 * province.
	 * 
	 * @return a map with the province as key and the total number of beds as value
	 */
	public Map<String, Integer> totalBedsNumberPerProvince() {
		return mountainHuts.stream().collect(Collectors.groupingBy(x -> x.getMunicipality().getProvince(),
				Collectors.summingInt(MountainHut :: getBedsNumber)
				));
	}

	/**
	 * Compute the maximum number of beds available in a single mountain hut per
	 * altitude range. If the altitude of the mountain hut is not available, use the
	 * altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the maximum number of beds
	 *         as value
	 */
	public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
		
		Map<String, Optional<Integer>> res = mountainHuts.stream().collect(Collectors.groupingBy(x->getAltitudeRange(x),
				Collectors.mapping(
						MountainHut :: getBedsNumber,
						Collectors.maxBy(Comparator.naturalOrder()))));
		return res;
	}

	/**
	 * Compute the municipality names per number of mountain huts in a municipality.
	 * The lists of municipality names must be in alphabetical order.
	 * 
	 * @return a map with the number of mountain huts in a municipality as key and a
	 *         list of municipality names as value
	 */
	public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
		return mountainHuts.stream().map(x -> x.getMunicipality().getName())
				.collect(Collectors.groupingBy(
						x -> x,
						TreeMap :: new,
						Collectors.counting()
						
						))
				.entrySet().stream()
				.collect(Collectors.groupingBy(
						Map.Entry :: getValue,
						Collectors.mapping(Map.Entry :: getKey,
										   Collectors.toList()
										)
						
						));
	}

}
