package com.aafv.lab02.config;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DotenvInitializerTest {

    @Mock
    private ConfigurableApplicationContext applicationContext;

    @Mock
    private ConfigurableEnvironment environment;

    @Mock
    private MutablePropertySources propertySources;

    @Captor
    private ArgumentCaptor<PropertySource<?>> propertySourceCaptor;

    private DotenvInitializer dotenvInitializer;

    @BeforeEach
    void setUp() {
        dotenvInitializer = new DotenvInitializer();
        when(applicationContext.getEnvironment()).thenReturn(environment);
        when(environment.getPropertySources()).thenReturn(propertySources);
    }

    @Test
    void testInitializeInvokesApplicationContext() {
        // Arrange: Mock environment setup
        when(applicationContext.getEnvironment()).thenReturn(environment);

        // Act
        dotenvInitializer.initialize(applicationContext);

        // Assert
        verify(applicationContext).getEnvironment();
        verify(environment).getPropertySources();
        verify(propertySources).addFirst(any(PropertySource.class));
    }

    @Test
    void testInitializeAddsPropertySourceWithDotenvName() {
        // Act
        dotenvInitializer.initialize(applicationContext);

        // Assert
        verify(propertySources).addFirst(propertySourceCaptor.capture());
        PropertySource<?> capturedSource = propertySourceCaptor.getValue();

        assertNotNull(capturedSource);
        assertEquals("dotenv", capturedSource.getName());
    }

    @Test
    void testInitializeHandlesNullProperties() {
        // Act & Assert - should not throw exception
        assertDoesNotThrow(() -> dotenvInitializer.initialize(applicationContext));
        verify(propertySources).addFirst(any(PropertySource.class));
    }
}
