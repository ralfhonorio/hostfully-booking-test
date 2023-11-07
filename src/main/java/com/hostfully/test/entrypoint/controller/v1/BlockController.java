package com.hostfully.test.entrypoint.controller.v1;

import com.hostfully.test.core.domain.Block;
import com.hostfully.test.core.domain.exceptions.BookingNotAvailableException;
import com.hostfully.test.core.domain.exceptions.DataNotFoundException;
import com.hostfully.test.core.usecase.block.CreateBlockUseCase;
import com.hostfully.test.core.usecase.block.DeleteBlockUseCase;
import com.hostfully.test.core.usecase.block.FindBlockUseCase;
import com.hostfully.test.core.usecase.block.UpdateBlockUseCase;
import com.hostfully.test.entrypoint.controller.v1.dto.BlockMapper;
import com.hostfully.test.entrypoint.controller.v1.dto.BlockRequest;
import com.hostfully.test.entrypoint.controller.v1.dto.BlockResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/blocks")
@Validated
public class BlockController {

    private final CreateBlockUseCase createBlockUseCase;
    private final UpdateBlockUseCase updateBlockUseCase;
    private final DeleteBlockUseCase deleteBlockUseCase;
    private final FindBlockUseCase findBlockUseCase;

    private final BlockMapper blockMapper;

    public BlockController(CreateBlockUseCase createBlockUseCase, UpdateBlockUseCase updateBlockUseCase, DeleteBlockUseCase deleteBlockUseCase, FindBlockUseCase findBlockUseCase, BlockMapper blockMapper) {
        this.createBlockUseCase = createBlockUseCase;
        this.updateBlockUseCase = updateBlockUseCase;
        this.deleteBlockUseCase = deleteBlockUseCase;
        this.findBlockUseCase = findBlockUseCase;
        this.blockMapper = blockMapper;
    }

    @Operation(summary = "Create a new block")
    @ApiResponse(responseCode = "201", description = "Block created successfully", content = @Content(mediaType = "application/json"))
    @PostMapping
    public ResponseEntity<BlockResponse> create(@RequestBody @Valid BlockRequest request) throws DataNotFoundException, BookingNotAvailableException {
        Block block = blockMapper.toBlock(request);
        Block createdBlock = createBlockUseCase.save(block);
        BlockResponse response = blockMapper.toBlockResponse(createdBlock);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing block")
    @ApiResponse(responseCode = "200", description = "Block updated successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Block not found")
    @PutMapping("/{id}")
    public ResponseEntity<BlockResponse> update(@PathVariable UUID id, @RequestBody @Valid BlockRequest request) throws DataNotFoundException {
        Optional<Block> optionalBlock = findBlockUseCase.findById(id);
        if (optionalBlock.isEmpty()) {
            throw new DataNotFoundException("Block not found");
        }
        Block block = blockMapper.toBlock(request);
        Block storedBlock = optionalBlock.get();

        block.setId(storedBlock.getId());
        block.setProperty(storedBlock.getProperty());
        block.setCreateAt(storedBlock.getCreateAt());

        Block updateBlock = updateBlockUseCase.update(block);
        BlockResponse response = blockMapper.toBlockResponse(updateBlock);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a block by ID")
    @ApiResponse(responseCode = "204", description = "Block deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<BlockResponse> delete(@PathVariable UUID id) {
        deleteBlockUseCase.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get all blocks")
    @ApiResponse(responseCode = "200", description = "List of all blocks", content = @Content(mediaType = "application/json"))
    @GetMapping
    public ResponseEntity<List<BlockResponse>> getAllBookings() {
        List<Block> blocks = findBlockUseCase.findAll();
        List<BlockResponse> responses = blocks.stream()
                .map(blockMapper::toBlockResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Operation(summary = "Find a block by ID")
    @ApiResponse(responseCode = "200", description = "Block found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Block not found")
    @GetMapping("{id}")
    public ResponseEntity<BlockResponse> findById(@PathVariable UUID id) throws DataNotFoundException {
        Optional<Block> optionalBlock = findBlockUseCase.findById(id);
        if (optionalBlock.isEmpty()) {
            throw new DataNotFoundException("Block not found");
        }
        BlockResponse blockResponse = blockMapper.toBlockResponse(optionalBlock.get());
        return ResponseEntity.ok(blockResponse);
    }
}
