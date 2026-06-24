package com.aafv.lab02.service;

import com.aafv.lab02.domain.entity.EntityC;
import com.aafv.lab02.repository.EntityRepository;
import com.aafv.lab02.service.imp.EntityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntityServiceTest {

    @Mock
    private EntityRepository entityRepository;

    @Captor
    private ArgumentCaptor<EntityC> entityCaptor;

    private EntityService entityService;
    private UUID testUUID;
    private EntityC testEntity;

    @BeforeEach
    void setUp() {
        entityService = new EntityServiceImpl(entityRepository);
        testUUID = UUID.randomUUID();
        testEntity = new EntityC(
            testUUID,
            "Harry Potter",
            "Stag",
            "Gryffindor",
            false
        );
    }

    // CREATE Tests
    @Test
    void testCreateEntitySuccess() {
        // Arrange
        EntityC newEntity = new EntityC(
            UUID.randomUUID(),
            "Hermione Granger",
            "Otter",
            "Gryffindor",
            false
        );

        // Act
        entityService.createEntity(newEntity);

        // Assert
        verify(entityRepository, times(1)).save(entityCaptor.capture());
        EntityC capturedEntity = entityCaptor.getValue();
        assertEquals("Hermione Granger", capturedEntity.getEntityName());
        assertEquals("Otter", capturedEntity.getEntityPatronus());
    }

    @Test
    void testCreateEntityCallsRepositorySave() {
        // Act
        entityService.createEntity(testEntity);

        // Assert
        verify(entityRepository).save(testEntity);
    }

    @Test
    void testCreateDeatheaterEntity() {
        // Arrange
        EntityC deatheater = new EntityC(
            UUID.randomUUID(),
            "Draco Malfoy",
            "Serpent",
            "Slytherin",
            true
        );

        // Act
        entityService.createEntity(deatheater);

        // Assert
        verify(entityRepository).save(deatheater);
    }

    // READ Tests
    @Test
    void testGetAllEntitiesSuccess() {
        // Arrange
        List<EntityC> mockEntities = Arrays.asList(
            new EntityC(UUID.randomUUID(), "Harry", "Stag", "Gryffindor", false),
            new EntityC(UUID.randomUUID(), "Severus", "Doe", "Slytherin", false)
        );
        when(entityRepository.findAll()).thenReturn(mockEntities);

        // Act
        List<EntityC> result = entityService.getAllEntities();

        // Assert
        assertEquals(2, result.size());
        verify(entityRepository).findAll();
    }

    @Test
    void testGetAllEntitiesEmpty() {
        // Arrange
        when(entityRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<EntityC> result = entityService.getAllEntities();

        // Assert
        assertTrue(result.isEmpty());
        verify(entityRepository).findAll();
    }

    @Test
    void testGetEntityByPatronusSuccess() {
        // Arrange
        String patronus = "Stag";
        List<EntityC> mockEntities = Arrays.asList(
            new EntityC(UUID.randomUUID(), "Harry", "Stag", "Gryffindor", false)
        );
        when(entityRepository.findByEntityPatronus(patronus)).thenReturn(mockEntities);

        // Act
        List<EntityC> result = entityService.getEntityByPatronus(patronus);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Harry", result.get(0).getEntityName());
        verify(entityRepository).findByEntityPatronus(patronus);
    }

    @Test
    void testGetEntityByPatronusNotFound() {
        // Arrange
        String patronus = "Phoenix";
        when(entityRepository.findByEntityPatronus(patronus)).thenReturn(Arrays.asList());

        // Act
        List<EntityC> result = entityService.getEntityByPatronus(patronus);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetOnlyDeatheaterSuccess() {
        // Arrange
        List<EntityC> deatheaters = Arrays.asList(
            new EntityC(UUID.randomUUID(), "Voldemort", "Snake", "Slytherin", true),
            new EntityC(UUID.randomUUID(), "Bellatrix", "None", "Slytherin", true)
        );
        when(entityRepository.findByIsDeatheaterTrue()).thenReturn(deatheaters);

        // Act
        List<EntityC> result = entityService.getOnlyDeatheater();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(EntityC::getIsDeatheater));
        verify(entityRepository).findByIsDeatheaterTrue();
    }

    @Test
    void testGetOnlyDeatheaterNone() {
        // Arrange
        when(entityRepository.findByIsDeatheaterTrue()).thenReturn(Arrays.asList());

        // Act
        List<EntityC> result = entityService.getOnlyDeatheater();

        // Assert
        assertTrue(result.isEmpty());
    }

    // UPDATE Tests
    @Test
    void testUpdateEntitySuccess() {
        // Arrange
        EntityC existingEntity = new EntityC(
            testUUID,
            "Harry Potter",
            "Stag",
            "Gryffindor",
            false
        );
        EntityC updatedData = new EntityC(
            testUUID,
            "Harry James Potter",
            "Phoenix",
            "Gryffindor",
            false
        );
        when(entityRepository.getById(testUUID)).thenReturn(existingEntity);

        // Act
        entityService.updateEntity(updatedData, testUUID);

        // Assert
        verify(entityRepository).getById(testUUID);
        verify(entityRepository).save(entityCaptor.capture());
        EntityC savedEntity = entityCaptor.getValue();
        assertEquals("Harry James Potter", savedEntity.getEntityName());
        assertEquals("Phoenix", savedEntity.getEntityPatronus());
    }

    @Test
    void testUpdateEntityAllFields() {
        // Arrange
        EntityC existing = new EntityC(testUUID, "Old Name", "Old", "Old House", false);
        EntityC updates = new EntityC(testUUID, "New Name", "New", "New House", true);
        when(entityRepository.getById(testUUID)).thenReturn(existing);

        // Act
        entityService.updateEntity(updates, testUUID);

        // Assert
        verify(entityRepository).save(entityCaptor.capture());
        EntityC result = entityCaptor.getValue();
        assertEquals("New Name", result.getEntityName());
        assertEquals("New", result.getEntityPatronus());
        assertEquals("New House", result.getEntityHouse());
        assertTrue(result.getIsDeatheater());
    }

    // DELETE Tests
    @Test
    void testDeleteEntitySuccess() {
        // Arrange
        when(entityRepository.findById(testUUID)).thenReturn(Optional.of(testEntity));

        // Act
        EntityC deletedEntity = entityService.deleteEntity(testUUID);

        // Assert
        verify(entityRepository).findById(testUUID);
        verify(entityRepository).delete(testEntity);
        assertEquals("Harry Potter", deletedEntity.getEntityName());
    }

    @Test
    void testDeleteEntityNotFound() {
        // Arrange
        UUID nonExistentId = UUID.randomUUID();
        when(entityRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> entityService.deleteEntity(nonExistentId));
        verify(entityRepository).findById(nonExistentId);
        verify(entityRepository, never()).delete(any());
    }

    @Test
    void testDeleteEntityThrowsCorrectException() {
        // Arrange
        UUID nonExistentId = UUID.randomUUID();
        when(entityRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> entityService.deleteEntity(nonExistentId)
        );
        assertEquals("Entity not found", exception.getMessage());
    }

    @Test
    void testDeleteReturnsDeletedEntity() {
        // Arrange
        when(entityRepository.findById(testUUID)).thenReturn(Optional.of(testEntity));

        // Act
        EntityC result = entityService.deleteEntity(testUUID);

        // Assert
        assertNotNull(result);
        assertEquals(testEntity.getId(), result.getId());
        assertEquals(testEntity.getEntityName(), result.getEntityName());
    }
}
