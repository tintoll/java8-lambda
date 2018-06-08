package stream.with.optional;

import java.util.Optional;

public class MobileTesterWithOptional {
	
	public static void main(String[] args) {
		
		// Null을 허용하지 않을 때는 Optional.of()
		ScreenResolution resolution = new ScreenResolution(750,1334);
		DisplayFeatures dfeatures = new DisplayFeatures("4.7", Optional.of(resolution));
		Mobile mobile = new Mobile(2015001, "Apple", "iPhone 6s", Optional.of(dfeatures));
		
		MobileService mService = new MobileService();
		
		int width = mService.getMobileScreenWidth(Optional.of(mobile));
		System.out.println("Apple iPhone 6s Screen Width = " + width);

		// Optional.of의 ifPresent(), isPresenet() 메서드 사용
		Optional<ScreenResolution> resol =  dfeatures.getResolution();
		resol.ifPresent(System.out::println);  // 존재하면 Comsumer형태로 출력을 하는용도
        System.out.println(resol.isPresent()); // 존재하는지 여부


		// Null을 허용하면 Optional.empty()	
		Mobile mobile2 = new Mobile(2015001, "Apple", "iPhone 6s", Optional.empty());		
		int width2 = mService.getMobileScreenWidth(Optional.of(mobile2));
		System.out.println("Apple iPhone 16s Screen Width = " + width2);

		// Optional.empty 의 ifPresent(), isPresenet() 메서드 사용
        Optional<DisplayFeatures> disply = mobile2.getDisplayFeatures();
        disply.ifPresent(System.out::println);  // 존재하면 Comsumer형태로 출력을 하는용도
        System.out.println(disply.isPresent()); // 존재하는지 여부 false
	}

}
