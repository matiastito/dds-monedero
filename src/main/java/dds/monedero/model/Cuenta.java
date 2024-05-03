package dds.monedero.model;

import static java.time.LocalDate.now;

import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {

  private int limiteDeExtraccionDiario;
  private double saldo;
  private List<Movimiento> movimientos;

  public Cuenta() {
    saldo = 0;
    movimientos = new ArrayList<>();
    limiteDeExtraccionDiario = 1000;
  }

  public Cuenta(double montoInicial) {
    saldo = montoInicial;
    movimientos = new ArrayList<>();
    limiteDeExtraccionDiario = 1000;
  }

  public void poner(double cuanto) {
    validarMontoPositivo(cuanto);
    validarSuperoCantidadDepositvosDiarios(3);
    new Deposito(now(), cuanto).agregateA(this);
  }

  public void sacar(double cuanto) {
    validarMontoPositivo(cuanto);
    validarSaldoSuficiente(cuanto);
    validarMaximoExtraccioonsDiarias(cuanto);
    new Extraccion(now(), cuanto).agregateA(this);
  }

  public void agregarMovimiento(Movimiento movimiento) {
    movimientos.add(movimiento);
  }

  public double getMontoExtraidoA(LocalDate fecha) {
    return getMovimientos().stream()
        .filter(movimiento -> movimiento.fueExtraido(fecha))
        .mapToDouble(Movimiento::getMonto)
        .sum();
  }

  public List<Movimiento> getMovimientos() {
    return movimientos;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  private void validarMontoPositivo(double cuanto) {
    if (cuanto <= 0) {
      throw new MontoNegativoException(cuanto + ": el monto a ingresar debe ser un valor positivo");
    }
  }

  private void validarSuperoCantidadDepositvosDiarios(int maxCantidadDepositosDiarios) {
    if (getMovimientos().stream().filter(Movimiento::isDeposito).count() >= maxCantidadDepositosDiarios) {
      throw new MaximaCantidadDepositosException("Ya excedio los " + maxCantidadDepositosDiarios + " depositos diarios");
    }
  }

  private void validarMaximoExtraccioonsDiarias(double cuanto) {
    double limite = limiteDeExtraccionDiario - getMontoExtraidoA(now());
    if (cuanto > limite) {
      throw new MaximoExtraccionDiarioException("No puede extraer mas de $ " + limiteDeExtraccionDiario
          + " diarios, límite: " + limite);
    }
  }

  private void validarSaldoSuficiente(double cuanto) {
    if (getSaldo() - cuanto < 0) {
      throw new SaldoMenorException("No puede sacar mas de " + getSaldo() + " $");
    }
  }

}
