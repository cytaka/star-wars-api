package takahara.cynthia.starwars.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import takahara.cynthia.starwars.dto.ErroDto;

@RestControllerAdvice
public class TratamentoErro {

	@Autowired
	private MessageSource message;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDto> tratar(MethodArgumentNotValidException exception) {
		List<ErroDto> listaErros = new ArrayList<ErroDto>();
		exception.getBindingResult().getFieldErrors().forEach(e -> {
			listaErros.add(new ErroDto(e.getField(), message.getMessage(e, LocaleContextHolder.getLocale())));
		});
		return listaErros;

	}
}
