import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder
import org.gatling.test.ExampleTest

object DebugSim {

  def main(args: Array[String]): Unit = {

    val simClass = classOf[ExampleTest].getName

    val props = new GatlingPropertiesBuilder
    props.simulationClass(simClass)

    Gatling.fromMap(props.build)
  }
}