package dds.monedero.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MonederoTest {
  private Cuenta cuenta;

  @BeforeEach
  void init() {
    cuenta = new Cuenta();
  }

  @Test
  void Poner() {
    assertEquals(cuenta.getSaldo(), 0);
    cuenta.poner(1500);
    assertEquals(cuenta.getSaldo(), 1500);
  }

  @Test
  void PonerMontoNegativo() {
    assertThrows(MontoNegativoException.class, () -> cuenta.poner(-1500));
  }

  @Test
  void TresDepositos() {
    assertEquals(cuenta.getSaldo(), 0);
    cuenta.poner(1500);
    assertEquals(cuenta.getSaldo(), 1500);
    cuenta.poner(456);
    assertEquals(cuenta.getSaldo(), 1956);
    cuenta.poner(1900);
    assertEquals(cuenta.getSaldo(), 3856);
  }

  @Test
  void MasDeTresDepositos() {
    assertThrows(MaximaCantidadDepositosException.class, () -> {
      assertEquals(cuenta.getSaldo(), 0);
      cuenta.poner(1500);
      assertEquals(cuenta.getSaldo(), 1500);
      cuenta.poner(456);
      assertEquals(cuenta.getSaldo(), 1956);
      cuenta.poner(1900);
      assertEquals(cuenta.getSaldo(), 3856);
      cuenta.poner(245);
      fail();
    });
  }

  @Test
  void ExtraerMasQueElSaldo() {
    assertThrows(SaldoMenorException.class, () -> {
      assertEquals(cuenta.getSaldo(), 0);
      cuenta.setSaldo(90);
      assertEquals(cuenta.getSaldo(), 90);
      cuenta.sacar(1001);
      fail();
    });
  }

  @Test
  public void ExtraerMasDe1000() {
    assertThrows(MaximoExtraccionDiarioException.class, () -> {
      assertEquals(cuenta.getSaldo(), 0);
      cuenta.setSaldo(5000);
      assertEquals(cuenta.getSaldo(), 5000);
      cuenta.sacar(1001);
      fail();
    });
  }

  @Test
  public void ExtraerMontoNegativo() {
    assertThrows(MontoNegativoException.class, () -> cuenta.sacar(-500));
  }

}