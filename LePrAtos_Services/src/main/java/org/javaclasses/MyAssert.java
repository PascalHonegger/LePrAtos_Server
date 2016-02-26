package org.javaclasses;

import javax.xml.bind.ValidationException;

public class MyAssert
{
	public static void NotNull(Object o, String errorMessage) throws ValidationException {
		if (o == null) {
			throw new ValidationException(errorMessage);
		}
	}

}
