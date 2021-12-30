package ait.nhatpc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ait.nhatpc.config.JwtTokenUtil;
import ait.nhatpc.dto.JwtRequest;
import ait.nhatpc.dto.JwtResponse;
import ait.nhatpc.dto.UserDTO;
import ait.nhatpc.service.IUserService;

@CrossOrigin
@RestController

public class UserAPI {
	@Autowired
	private IUserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping(value = "/register")
	public ResponseEntity<?> createUser(@RequestBody UserDTO dto) {
		return ResponseEntity.ok(userService.save(dto));
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());

		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUserName());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
