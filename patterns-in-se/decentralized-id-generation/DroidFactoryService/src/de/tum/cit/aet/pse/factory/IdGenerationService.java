package de.tum.cit.aet.pse.factory;

import java.time.Instant;

public class IdGenerationService {

	private static final int FACTORY_ID_BITS = 4;
	private static final int SEQUENCE_BITS = 6;

	private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;


	private long lastTimestamp = -1L;
	private long sequence = 0L;

	public synchronized long generateId(int factoryId) {
		long currentTimestamp = timestamp();
		if (currentTimestamp == lastTimestamp) {
			sequence += 1;
			sequence &= MAX_SEQUENCE;
		}
		long result = currentTimestamp;
		result <<= 4;
		result |= factoryId;
		result <<= 6;
		result |= sequence;

		lastTimestamp = currentTimestamp;

		return  result;
	}


	// Get current timestamp in milliseconds and the difference to the UNIX epoch Jan, 1st 1970.
	private long timestamp() {
		return Instant.now().toEpochMilli();
	}

}
