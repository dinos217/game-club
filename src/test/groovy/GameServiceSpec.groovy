import com.project.gameclub.dtos.GameDto
import com.project.gameclub.dtos.GameRequestDto
import com.project.gameclub.entities.Game
import com.project.gameclub.repositories.GameRepository
import com.project.gameclub.repositories.GenreRepository
import com.project.gameclub.services.GameService
import com.project.gameclub.services.GameServiceImpl
import spock.lang.Specification

class GameServiceSpec extends Specification {

    GameRepository gameRepository = Mock(GameRepository)
    GenreRepository genreRepository = Mock(GenreRepository)

    GameService service = new GameServiceImpl(gameRepository, genreRepository)

    def "save a new game"() {

        Set genres = new HashSet<>()
        genres.add("Role Playing")

        given: "any new game"
        GameRequestDto gameRequestDto = Mock(GameRequestDto)
        GameDto gameDto = Mock(GameDto)
        Game game = new Game()
        Optional<Game> gameOpt = Optional.empty()

        gameRequestDto.setGameGenres(genres)

        when: "the repo method is called"
        service.save(gameRequestDto)

        then: ""
        1 * service.gameRepository.findByTitleAndStudio(_, _) >> gameOpt
        1 * service.gameRepository.save(_) >> game

    }

    def "delete an existing game"() {

        given:
        Game game = new Game()

        when:
        service.delete(1L)

        then:
        1 * service.gameRepository.findById(_ as Long) >> Optional.of(game)
        1 * service.gameRepository.delete(game)
    }

    def "try deleting game which does not exist"() {

        given: "an empty optional"
        Optional<Game> gameOpt = Optional.empty()

        when: "the delete method is called"
        service.delete(1L)

        then:
        1 * service.gameRepository.findById(_ as Long) >> gameOpt
        Exception ex = thrown()
        ex.getMessage() == "Could not find game with id: 1"
    }

}
