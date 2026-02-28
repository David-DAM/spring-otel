# Spring Boot + OpenTelemetry + Grafana Cloud

Este proyecto es una aplicación de demostración de **Spring Boot** que implementa observabilidad completa utilizando *
*OpenTelemetry** y **Grafana Alloy** para enviar métricas, trazas y logs a **Grafana Cloud**.

## Tabla de Contenidos

- [Descripción](#descripción)
- [Tecnologías](#tecnologías)
- [Requisitos Previos](#requisitos-previos)
- [Configuración](#configuración)
- [Variables de Entorno](#variables-de-entorno)

## Descripción

Este proyecto demuestra cómo integrar una aplicación Spring Boot con el stack de observabilidad de Grafana utilizando
OpenTelemetry (OTel). Incluye:

- **Trazas distribuidas**: Seguimiento de solicitudes a través de la aplicación
- **Métricas**: Recopilación de métricas de rendimiento y uso
- **Logs estructurados**: Correlación de logs con trazas mediante trace IDs
- **Profiling continuo**: Integración con Pyroscope para análisis de rendimiento

## Tecnologías

- **Java 25** (JDK)
- **Spring Boot** con Spring MVC
- **OpenTelemetry** - Instrumentación y telemetría
- **Grafana Alloy** - Colector de telemetría (sucesor de Grafana Agent)
- **Grafana Cloud** - Backend de observabilidad
- **Pyroscope** - Profiling continuo
- **Docker Compose** - Orquestación de servicios
- **Lombok** - Reducción de código boilerplate
- **Maven** - Gestión de dependencias

## Requisitos Previos

- **Java 25** o superior
- **Docker** y **Docker Compose**
- Cuenta de **Grafana Cloud** (gratuita disponible)

## Configuración

### 1. Variables de Entorno para Grafana Cloud

Crea un archivo `.env` en la raíz del proyecto o configura las siguientes variables:

```bash
Endpoint OTLP de Grafana Cloud
GRAFANA_CLOUD_OTLP_ENDPOINT=https://otlp-gateway-.grafana.net/otlp
ID de instancia de Grafana
GRAFANA_INSTANCE_ID=tu-instance-id
API Key con permisos de escritura
GCLOUD_RW_API_KEY=tu-api-key
Endpoint de Fleet Management (opcional)
GRAFANA_CLOUD_FLEET_ENDPOINT=https://your-fleet-endpoint
Endpoint de Pyroscope
GRAFANA_CLOUD_PYROSCOPE_ENDPOINT=https://profiles-prod-.grafana.net``` 
```

### 2. Configuración de Alloy

El archivo `docker-config/alloy/config.alloy` contiene la configuración del colector que:

- Recibe datos OTLP en puertos `4317` (gRPC) y `4318` (HTTP)
- Detecta recursos del sistema (hostname, OS)
- Elimina atributos innecesarios de los recursos
- Agrupa datos en lotes antes de enviar
- Exporta a Grafana Cloud con autenticación básica
- Recibe perfiles de Pyroscope en el puerto `9999`

## Variables de Entorno

| Variable                           | Descripción                       |
|------------------------------------|-----------------------------------|
| `GRAFANA_CLOUD_OTLP_ENDPOINT`      | Endpoint OTLP de Grafana Cloud    |
| `GRAFANA_INSTANCE_ID`              | ID de tu instancia de Grafana     |
| `GCLOUD_RW_API_KEY`                | API Key con permisos de escritura |
| `GRAFANA_CLOUD_PYROSCOPE_ENDPOINT` | Endpoint de Pyroscope             | 
| `GRAFANA_CLOUD_FLEET_ENDPOINT`     | Endpoint de Fleet Management      |

## Visualización en Grafana

Una vez que la aplicación esté en ejecución y enviando datos:

1. **Trazas**: Ve a Grafana Cloud → Explore → Selecciona Tempo
2. **Métricas**: Grafana Cloud → Explore → Selecciona Mimir/Prometheus
3. **Logs**: Grafana Cloud → Explore → Selecciona Loki
4. **Profiles**: Grafana Cloud → Profiles → Pyroscope

## Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

---

Desarrollado por **DavinchiCoder** 