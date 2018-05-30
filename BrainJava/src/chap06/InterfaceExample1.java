package chap06;

public class InterfaceExample1 {
	public static void main(String[] args) {
		// Lendable obj1 = new SeparateVolume("863?774개", "개미", "베르베르");

		// SeparateVolume obj1 = new SeparateVolume("863?774개", "개미", "베르베르");

		Lendable obj = new SeparateVolume("863?774개", "개미", "베르베르");

		// AppCDInfo obj2 = new AppCDInfo("2005-7001", "Redhat Fedara");

		// Lendable obj2 = new AppCDInfo("2005-7001", "Redhat Fedara");

		try {
			obj.checkOut("김녕숙", "20060315");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// obj2.checkOut("박휘경", "20060317");
		obj.checkIn();
		// obj2.checkIn();

		obj = new AppCDInfo("2005-7001", "Redhat Fedara");
		try {
			obj.checkOut("박휘경", "20060317");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		obj.checkIn();

	}
}
