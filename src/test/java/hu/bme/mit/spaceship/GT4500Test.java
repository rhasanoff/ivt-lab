package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockFirst;
  private TorpedoStore mockSecond;

  @BeforeEach
  public void init(){
    mockFirst = mock(TorpedoStore.class);
    mockSecond = mock(TorpedoStore.class);
    ship = new GT4500(mockFirst, mockSecond);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockFirst.isEmpty()).thenReturn(false);
    when(mockFirst.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    verify(mockFirst, times(1)).isEmpty();
    verify(mockFirst, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockFirst.isEmpty()).thenReturn(false);
    when(mockSecond.isEmpty()).thenReturn(false);
    when(mockFirst.fire(1)).thenReturn(true);
    when(mockSecond.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);

    verify(mockFirst, times(1)).isEmpty();
    verify(mockSecond, times(1)).isEmpty();
    verify(mockFirst, times(1)).fire(1);
    verify(mockSecond, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_EmptyTorpedos(){
    when(mockFirst.isEmpty()).thenReturn(true);
    when(mockSecond.isEmpty()).thenReturn(true);
    when(mockFirst.fire(1)).thenReturn(false);
    when(mockSecond.fire(1)).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(false, result);
    
    verify(mockFirst, times(1)).isEmpty();
    verify(mockSecond, times(1)).isEmpty();
    verify(mockFirst, times(0)).fire(1);
    verify(mockSecond, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_FirstTorpedoEmpty(){
    when(mockFirst.isEmpty()).thenReturn(true);
    when(mockSecond.isEmpty()).thenReturn(false);
    when(mockFirst.fire(1)).thenReturn(false);
    when(mockSecond.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true, result);
    
    verify(mockFirst, times(1)).isEmpty();
    verify(mockSecond, times(1)).isEmpty();
    verify(mockFirst, times(0)).fire(1);
    verify(mockSecond, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_SecondTorpedoEmpty(){
    when(mockFirst.isEmpty()).thenReturn(false);
    when(mockSecond.isEmpty()).thenReturn(true);
    when(mockFirst.fire(1)).thenReturn(true);
    when(mockSecond.fire(1)).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true, result);
    
    verify(mockFirst, times(1)).isEmpty();
    verify(mockSecond, times(0)).isEmpty();
    verify(mockFirst, times(1)).fire(1);
    verify(mockSecond, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Alternate(){
    when(mockFirst.isEmpty()).thenReturn(false);
    when(mockSecond.isEmpty()).thenReturn(true);
    when(mockFirst.fire(1)).thenReturn(true);
    when(mockSecond.fire(1)).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    when(mockFirst.isEmpty()).thenReturn(true);

    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true, result);
    assertEquals(false, result2);
    
    verify(mockFirst, times(2)).isEmpty();
    verify(mockSecond, times(1)).isEmpty();
    verify(mockFirst, times(1)).fire(1);
    verify(mockSecond, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_FirstTorpedoEmpty(){
    when(mockFirst.isEmpty()).thenReturn(true);
    when(mockSecond.isEmpty()).thenReturn(false);
    when(mockFirst.fire(1)).thenReturn(false);
    when(mockSecond.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.ALL);

    assertEquals(false, result);
    
    verify(mockFirst, times(1)).isEmpty();
    verify(mockSecond, times(0)).isEmpty();
    verify(mockFirst, times(0)).fire(1);
    verify(mockSecond, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_SecondTorpedoEmpty(){
    when(mockFirst.isEmpty()).thenReturn(false);
    when(mockSecond.isEmpty()).thenReturn(true);
    when(mockFirst.fire(1)).thenReturn(true);
    when(mockSecond.fire(1)).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.ALL);

    assertEquals(false, result);
    
    verify(mockFirst, times(1)).isEmpty();
    verify(mockSecond, times(1)).isEmpty();
    verify(mockFirst, times(0)).fire(1);
    verify(mockSecond, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_AlternateSecondary(){
    when(mockFirst.isEmpty()).thenReturn(false);
    when(mockSecond.isEmpty()).thenReturn(false);
    when(mockFirst.fire(1)).thenReturn(true);
    when(mockSecond.fire(1)).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    when(mockSecond.fire(1)).thenReturn(true);

    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true, result);
    assertEquals(true, result2);
    
    verify(mockFirst, times(1)).isEmpty();
    verify(mockSecond, times(1)).isEmpty();
    verify(mockFirst, times(1)).fire(1);
    verify(mockSecond, times(1)).fire(1);
  }
  @Test
  public void fireTorpedo_Single_AlternatePrimaryAgain(){
    when(mockFirst.isEmpty()).thenReturn(false);
    when(mockSecond.isEmpty()).thenReturn(true);
    when(mockFirst.fire(1)).thenReturn(true);
    when(mockSecond.fire(1)).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    when(mockFirst.fire(1)).thenReturn(true);

    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true, result);
    assertEquals(true, result2);
    
    verify(mockFirst, times(2)).isEmpty();
    verify(mockSecond, times(1)).isEmpty();
    verify(mockFirst, times(2)).fire(1);
    verify(mockSecond, times(0)).fire(1);
  }
  @Test
  public void fireLaser(){
    // Arrange

    // Act
    boolean result = ship.fireLaser(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }
}
