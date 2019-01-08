package ua.nure.balagura.practice6;

public class Parking {

	private Object[] parkingPlaces = null;

	public static void main(String[] args) {

	}

	public Parking(int n) {
		parkingPlaces = new Object[n];
	}

	public boolean arrive(Object obj, int n) {
		if (obj == null)
			throw new IllegalArgumentException("Parking cannot accept nothing! @param obj is null");
		if (n >= parkingPlaces.length)
			throw new IllegalArgumentException("There are not so many places! @param " + n + " over the plase count");
		for (Object object : parkingPlaces) {
			if (obj.equals(object))
				throw new IllegalArgumentException("@param " + obj + " is already parked!");
		}
		for (int i = n; i < parkingPlaces.length; i++) {
			if (parkingPlaces[i] == null) {
				parkingPlaces[i] = obj;
				return true;
			}
		}
		return false;
	}

	public boolean depart(Object obj) {
		if (obj == null)
			throw new IllegalArgumentException("Parking cannot depart nothing! @param obj is null");
		for (int i = 0; i < parkingPlaces.length; i++) {
			if (obj.equals(parkingPlaces[i])) {
				parkingPlaces[i] = null;
				return true;
			}
		}
		return false;
	}

	public void status() {
		StringBuilder sb = new StringBuilder("{ Parking plases: ");
		for (int i = 0; i < parkingPlaces.length; i++) {
			sb.append("Pl." + i + " [");
			if (parkingPlaces[i] == null) {
				sb.append(' ');
			} else {
				sb.append('X');
			}
			sb.append("]; ");
		}
		sb.append("}");
		System.out.println(sb);
	}
}
