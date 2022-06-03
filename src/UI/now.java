package UI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class now {

	public static void main(String[] args) {
		
		LocalDate now = LocalDate.now();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
		String fmtnow = now.format(fmt);
		
		System.out.print(fmtnow);

	}

}
