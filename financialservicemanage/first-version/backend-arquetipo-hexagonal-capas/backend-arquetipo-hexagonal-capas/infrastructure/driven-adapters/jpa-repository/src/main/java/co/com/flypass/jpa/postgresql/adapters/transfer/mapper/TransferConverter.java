package co.com.flypass.jpa.postgresql.adapters.transfer.mapper;


import co.com.flypass.jpa.postgresql.adapters.product.entity.ProductEntity;
import co.com.flypass.jpa.postgresql.adapters.transfer.constants.TransferConstants;
import co.com.flypass.jpa.postgresql.adapters.transfer.entity.TransferEntity;
import co.com.flypass.models.Transfer;

import java.util.List;

public class TransferConverter
{

    public static TransferEntity convertTransferToEntity(Transfer transfer) {
        transfer.validTransfer(); // Validar transferencia
        TransferEntity entity = new TransferEntity();
        entity.setCuentaEnvio(convertLongToProduct(transfer.getIdCuentaEnvio()));
        entity.setCuentaRecepcion(convertLongToProduct(transfer.getIdCuentaDestino()));
        entity.setMonto(transfer.getMonto());
        entity.setFechaTransferencia(transfer.getFechaTransferencia());
        entity.setEstado(TransferEntity.EstadoTransferencia.valueOf(transfer.getEstado()));
        entity.setNumeroReferencia(transfer.getNumeroReferencia());
        entity.setDescripcion(transfer.getDescripcion());
        return entity;
    }
    public static ProductEntity convertLongToProduct(Long cuentaId) {
        return ProductEntity.builder()
                .id(cuentaId)
                .build();

    }

    public TransferEntity.EstadoTransferencia convertStringToTransferState(String estadoTransferencia) {
        if (TransferConstants.TRANSFER_COMPLETE.equals(estadoTransferencia)) {
            return TransferEntity.EstadoTransferencia.COMPLETADA;
        } else if (TransferConstants.TRANSFER_PENDING.equals(estadoTransferencia)) {
            return TransferEntity.EstadoTransferencia.PENDIENTE;
        } else if (TransferConstants.TRANSFER_CANCELLED.equals(estadoTransferencia)) {
            return TransferEntity.EstadoTransferencia.CANCELADA;
        }
        return null;
    }

    public static Transfer convertEntityToTransfer(TransferEntity entity) {
        if (entity == null) {
            return null;
        }
        return Transfer.builder()
                .idCuentaEnvio(entity.getCuentaEnvio().getId())
                .idCuentaDestino(entity.getCuentaRecepcion().getId())
                .monto(entity.getMonto())
                .fechaTransferencia(entity.getFechaTransferencia())
                .estado(entity.getEstado().toString())
                .numeroReferencia(entity.getNumeroReferencia())
                .descripcion(entity.getDescripcion())
                .build();
    }

    public static List<Transfer> toTransferList(List<TransferEntity> entities) {
        return entities.stream()
                .map(TransferConverter::convertEntityToTransfer)
                .toList();
    }

    public static List<TransferEntity> toEntityList(List<Transfer> transfers) {
        return transfers.stream()
                .map(TransferConverter::convertTransferToEntity)
                .toList();
    }
}
