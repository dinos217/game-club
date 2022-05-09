import com.project.gameclub.dtos.RentalRequestDto
import com.project.gameclub.entities.Game
import com.project.gameclub.repositories.GameRepository
import com.project.gameclub.repositories.RentalRepository
import com.project.gameclub.services.RentalService
import com.project.gameclub.services.RentalServiceImpl
import spock.lang.Specification

class RentalServiceSpec extends Specification {

    GameRepository gameRepository = Mock(GameRepository)
    RentalRepository rentalRepository = Mock(RentalRepository)

    RentalService service = new RentalServiceImpl(gameRepository, rentalRepository)

    def "try renting a game which does not exist"() {

        given: "a game which is not in db"
        RentalRequestDto rentalRequestDto = new RentalRequestDto()
        rentalRequestDto.setGameId(1L)
        Optional<Game> gameOpt = Optional.empty()

        when: "loanGame method is called"
        service.loanGame(rentalRequestDto)

        then:
        1 * service.gameRepository.findById(1L) >> gameOpt
        Exception ex = thrown()
        ex.getMessage() == "Game with id: 1 does not exist in database."
    }

    def "try renting a game which is already rented"() {



    }

}
