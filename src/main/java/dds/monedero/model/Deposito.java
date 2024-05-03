package dds.monedero.model;

import java.time.LocalDate;

public class Deposito implements Movimiento {
  private LocalDate fecha;
  private double monto;

  public Deposito(LocalDate fecha, double monto) {
    this.fecha = fecha;
    this.monto = monto;
  }

  @Override
  public double getMonto() {
    return monto;
  }

  @Override
  public LocalDate getFecha() {
    return fecha;
  }

  @Override
  public boolean fueDepositado(LocalDate fecha) {
    return this.esDeLaFecha(fecha);
  }

  @Override
  public boolean fueExtraido(LocalDate fecha) {
    return false;
  }

  @Override
  public boolean esDeLaFecha(LocalDate fecha) {
    return this.fecha.equals(fecha);
  }

  @Override
  public boolean isDeposito() {
    return true;
  }

  @Override
  public boolean isExtraccion() {
    return false;
  }

  @Override
  public void agregateA(Cuenta cuenta) {
    cuenta.setSaldo(calcularValor(cuenta));
    cuenta.agregarMovimiento(this);
  }

  @Override
  public double calcularValor(Cuenta cuenta) {
    return cuenta.getSaldo() + getMonto();
  }
}
