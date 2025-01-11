// hooms-N1 [NeM3YbFmN1]
(function() {
  $(function() {
    $(".hooms-N1").each(function() {
      const $block = $(this);
      // Swiper
      const swiper = new Swiper(".hooms-N1 .contents-swiper", {
        slidesPerView: 1,
        spaceBetween: 0,
        loop: true,
        autoplay: {
          delay: 5000,
        },
        pagination: {
          el: ".hooms-N1 .swiper-pagination",
          clickable: true,
        },
        navigation: {
          nextEl: ".hooms-N1 .swiper-button-next",
          prevEl: ".hooms-N1 .swiper-button-prev",
        },
      });
      // Swiper Play, Pause Button
      const pauseButton = $block.find('.swiper-button-pause');
      const playButton = $block.find('.swiper-button-play');
      playButton.hide();
      pauseButton.show();
      pauseButton.on('click', function() {
        swiper.autoplay.stop();
        playButton.show();
        pauseButton.hide();
      });
      playButton.on('click', function() {
        swiper.autoplay.start();
        playButton.hide();
        pauseButton.show();
      });
    });
  });
})();
// hooms-N7 [rEM3YBfN57]
(function() {
  $(function() {
    $(".hooms-N7").each(function() {
      const $block = $(this);
      // Swiper
      const swiper = new Swiper(".hooms-N7 .contents-swiper", {
        slidesPerView: 'auto',
        loop: true,
        pagination: {
          type: "progressbar",
          el: ".hooms-N7 .swiper-pagination",
          clickable: true,
        },
        autoplay: {
          delay: 2500,
        },
        on: {
          slideChange: function() {
            $block.find('.contents-slide').removeClass('contents-price-active');
            const activeIndex = this.activeIndex % this.slides.length;
            $block.find('.contents-slide').eq(activeIndex).addClass('contents-price-active');
          }
        }
      });
    });
  });
})();;
// hooms-N45 [LEm3ZGqFVt]
(function() {
  $(function() {
    $(".hooms-order-N1").each(function() {
      const $block = $(this);
      // order Textarea Count
      $block.find("textarea").on("keyup", function() {
        var orderText = $(this).val();
        if (orderText.length == 0 || orderText == "") {
          $block.find(".order-text-count").text("0자");
        } else {
          $block.find(".order-text-count").text(orderText.length);
        }
        if (orderText.length > 4000) {
          $(this).val($(this).val().substring(0, 4000));
        }
      });
      // order File Cancel Button
      $block.find('.order-form-file').each(function() {
        const $this = $(this);
        $this.find('.fileset-input').on('change', function() {
          $(this).siblings('.fileset-btn-cancel').show();
        });
        $this.find('.fileset-btn-cancel').on('click', function() {
          $(this).siblings('.fileset-input').val('');
          $(this).hide();
        });
      });
    });
  });
})();;
// hooms-N39 [WdM3Zh09l4]
(function() {
  $(function() {
    $(".hooms-N39").each(function() {
      const $block = $(this);
      // modalSet
      modalSet(".modalset-dim", "#modalSet1", "#modalSet2");

      function modalSet(dim, modalSet1, modalSet2) {
        // Modal Show
        $block.find('.modalset-btn').on('click', function() {
          $block.find(modalSet1).addClass('modalset-active');
          $block.find(dim).fadeIn();
        });
        // Modal Hide
        $block.find('.modalset-dim, .modal-close').on('click', function() {
          $block.find(modalSet1).removeClass('modalset-active');
          $block.find(modalSet2).removeClass('modalset-active');
          $block.find(dim).fadeOut();
        });
        $block.find(modalSet1).find('.btnset-confirm').on('click', function() {
          $block.find(modalSet1).removeClass('modalset-active');
          $block.find(modalSet2).addClass('modalset-active');
        });
        // Modal2 Show
        $block.find(modalSet2).find('.btnset-confirm').on('click', function() {
          $block.find(modalSet2).removeClass('modalset-active');
          $block.find(dim).fadeOut();
        });
      };
    });
  });
})();;
// hooms-N27 [vum3ZgkElV]
(function() {
  $(function() {
    $(".hooms-N27").each(function() {
      const $block = $(this);
      // Swiper
      const swiper = new Swiper(".hooms-N27 .contents-swiper", {
        slidesPerView: 'auto',
        centeredSlides: true,
        loop: true,
        pagination: {
          type: "progressbar",
          el: ".hooms-N27 .swiper-pagination",
          clickable: true,
        },
        autoplay: {
          delay: 2500,
        },
        on: {
          slideChange: function() {
            $block.find('.contents-slide').removeClass('contents-price-active');
            const activeIndex = this.activeIndex % this.slides.length;
            $block.find('.contents-slide').eq(activeIndex).addClass('contents-price-active');
          }
        }
      });
    });
  });
})();;
// hooms-N34 [Tym3ZgKEPc]
(function() {
  $(function() {
    $(".hooms-N34").each(function() {
      const $block = $(this);
      // Swiper
      const swiper = new Swiper(".hooms-N34 .contents-swiper", {
        slidesPerView: 'auto',
        centeredSlides: true,
        loop: true,
        pagination: {
          type: "progressbar",
          el: ".hooms-N34 .swiper-pagination",
          clickable: true,
        },
        autoplay: {
          delay: 2500,
        },
      });
    });
  });
})();;
// hooms-N53 [nvm3zH37hv]
(function() {
  $(function() {
    $(".hooms-contact-N3").each(function() {
      const $block = $(this);
      // Textarea Count
      $block.find("textarea").on("keyup", function() {
        var contactText = $(this).val();
        if (contactText.length == 0 || contactText == "") {
          $block.find(".contact-text-count").text("0");
        } else {
          $block.find(".contact-text-count").text(contactText.length);
        }
        if (contactText.length > 4000) {
          $(this).val($(this).val().substring(0, 4000));
        }
      });
      // File Cancel Button
      $block.find('.contact-form-file').each(function() {
        const $this = $(this);
        $this.find('.fileset-input').on('change', function() {
          $(this).siblings('.fileset-btn-cancel').show();
        });
        $this.find('.fileset-btn-cancel').on('click', function() {
          $(this).siblings('.fileset-input').val('');
          $(this).hide();
        });
      });
    });
  });
})();;