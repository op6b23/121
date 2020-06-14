<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="site-section">
	<div class="container">
		<div class="row">
			<div class="col-lg-8 single-content">
				<h1 class="mb-4">${petsitter.boardTitle}</h1>
				<div class="post-meta d-flex mb-5">
					<div class="vcard">
						<span class="d-block"><a href="#">${petsitter.userID}</a></span> 
						<span class="date-read">${petsitter.boardDate} 
						<span class="mx-1">&bullet;</span> 조회 ${petsitter.boardHit} 
						<span class="icon-star2"></span></span>
					</div>
				</div>
				<table class="table">
					<tbody>
					<tr align="center">
						<td><span style="font-weight:bold;">크기</span></td>
						<td>${size}</td>
					</tr>
					<tr align="center">
						<td><span style="font-weight:bold;">요일</span></td>
						<td>${day}</td>
					</tr>
					<tr align="center">
						<td><span style="font-weight:bold;">요금</span></td>
						<td>${petsitter.petPrice}</td>
					</tr>
					<tr align="center">
						<td><span style="font-weight:bold;">지역</span></td>
						<td>${petsitter.petLocal}</td>
					</tr>
					<tr><td></td><td></td></tr>
					</tbody>
				</table>
				<br /> <br />
				${petsitter.boardContent}
				
				
				<!-- 추가 -->
				<div class="pt-5" align="right">
					<button type="submit" formaction="petsitterDetail/edit"
						class="btn">수정</button>
					<a href="<c:url value="/petsitterDetail/delete">
						<c:param name="boardNum" value="${petsitter.boardNum}"/>
						</c:url>"><input type="submit" class="btn" value="삭제" /></a>
				</div>

				<div class="pt-5" align="center">
					<a href="petsitterDetail/likePlus"><img
						src="resources/img/love.png" border="0" class="zoom"></a>
					<p>추천수 : 1</p>
				</div>
				<div class="pt-5" align="center">
					<a href="petsitterList"><input type="button" value="목록" class="btn" /></a>
				</div>

				<!-- comment 작성 부분 -->
				<div class="comment-form-wrap pt-5">
					<div class="section-title">
						<h2 class="mb-5">Leave a comment</h2>
					</div>
					<form action="#" class="p-5 bg-light">
						<div class="form-group">
							<label for="message">&nbsp&nbsp댓글 쓰기</label>
							<textarea name="" id="message" cols="10" rows="5"
								class="form-control"></textarea>
						</div>
						<div class="form-group">
							<input type="submit" value="등록" class="btn btn-primary py-3">
						</div>
					</form>
				</div>

				<!-- comment 시작 부분 -->

				<div class="pt-5">
					<div class="section-title">
						<h2 class="mb-5">6 Comments</h2>
					</div>
					<ul class="comment-list">
						<li class="comment">
							<div class="vcard bio">
								<img src="images/person_1.jpg" alt="Image placeholder">
							</div>
							<div class="comment-body">
								<h3>Jean Doe</h3>
								<div class="meta">January 9, 2018 at 2:21pm</div>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
									Pariatur quidem laborum necessitatibus, ipsam impedit vitae
									autem, eum officia, fugiat saepe enim sapiente iste iure! Quam
									voluptas earum impedit necessitatibus, nihil?</p>
								<p>
									<a href="#" class="reply">Reply</a>
								</p>
							</div>
						</li>

						<li class="comment">
							<div class="vcard bio">
								<img src="images/person_1.jpg" alt="Image placeholder">
							</div>
							<div class="comment-body">
								<h3>Jean Doe</h3>
								<div class="meta">January 9, 2018 at 2:21pm</div>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
									Pariatur quidem laborum necessitatibus, ipsam impedit vitae
									autem, eum officia, fugiat saepe enim sapiente iste iure! Quam
									voluptas earum impedit necessitatibus, nihil?</p>
								<p>
									<a href="#" class="reply">Reply</a>
								</p>
							</div>

							<ul class="children">
								<li class="comment">
									<div class="vcard bio">
										<img src="images/person_1.jpg" alt="Image placeholder">
									</div>
									<div class="comment-body">
										<h3>Jean Doe</h3>
										<div class="meta">January 9, 2018 at 2:21pm</div>
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing
											elit. Pariatur quidem laborum necessitatibus, ipsam impedit
											vitae autem, eum officia, fugiat saepe enim sapiente iste
											iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
										<p>
											<a href="#" class="reply">Reply</a>
										</p>
									</div>


									<ul class="children">
										<li class="comment">
											<div class="vcard bio">
												<img src="images/person_1.jpg" alt="Image placeholder">
											</div>
											<div class="comment-body">
												<h3>Jean Doe</h3>
												<div class="meta">January 9, 2018 at 2:21pm</div>
												<p>Lorem ipsum dolor sit amet, consectetur adipisicing
													elit. Pariatur quidem laborum necessitatibus, ipsam impedit
													vitae autem, eum officia, fugiat saepe enim sapiente iste
													iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
												<p>
													<a href="#" class="reply">Reply</a>
												</p>
											</div>

											<ul class="children">
												<li class="comment">
													<div class="vcard bio">
														<img src="images/person_1.jpg" alt="Image placeholder">
													</div>
													<div class="comment-body">
														<h3>Jean Doe</h3>
														<div class="meta">January 9, 2018 at 2:21pm</div>
														<p>Lorem ipsum dolor sit amet, consectetur adipisicing
															elit. Pariatur quidem laborum necessitatibus, ipsam
															impedit vitae autem, eum officia, fugiat saepe enim
															sapiente iste iure! Quam voluptas earum impedit
															necessitatibus, nihil?</p>
														<p>
															<a href="#" class="reply">Reply</a>
														</p>
													</div>
												</li>
											</ul>
										</li>
									</ul>
								</li>
							</ul>
						</li>

						<li class="comment">
							<div class="vcard bio">
								<img src="images/person_1.jpg" alt="Image placeholder">
							</div>
							<div class="comment-body">
								<h3>Jean Doe</h3>
								<div class="meta">January 9, 2018 at 2:21pm</div>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
									Pariatur quidem laborum necessitatibus, ipsam impedit vitae
									autem, eum officia, fugiat saepe enim sapiente iste iure! Quam
									voluptas earum impedit necessitatibus, nihil?</p>
								<p>
									<a href="#" class="reply">Reply</a>
								</p>
							</div>
						</li>
					</ul>
					<!-- END comment-list -->

				</div>
			</div>
		</div>
	</div>
</div>