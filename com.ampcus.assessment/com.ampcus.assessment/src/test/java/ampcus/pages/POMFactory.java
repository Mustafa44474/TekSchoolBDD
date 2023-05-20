package ampcus.pages;

import ampcus.base.BaseSetup;

public class POMFactory extends BaseSetup {

	private static POMFactory pomFactory;
	private AmpucsContactPage contactPage;
	
	private POMFactory() {
		this.contactPage = new AmpucsContactPage();
	}

	public static POMFactory getPOMFactory() {
		if (pomFactory == null)
			pomFactory = new POMFactory();
		return pomFactory;
	}

	public AmpucsContactPage contactPage() {
		return this.contactPage;
	}

}
