package com.hostfully.test.core.usecase.block;

import com.hostfully.test.core.domain.Block;
import com.hostfully.test.core.domain.exceptions.BookingNotAvailableException;
import com.hostfully.test.core.domain.exceptions.DataNotFoundException;

public interface CreateBlockUseCase {

    Block save(Block block) throws DataNotFoundException, BookingNotAvailableException;
}
