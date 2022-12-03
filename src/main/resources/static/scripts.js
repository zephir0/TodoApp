$(document).on('click', 'span.TaskDescription', function() {
    let text = $(this).text().trim();
    let span = $(this).closest('.task-description') //get closest div
    const textarea = $('<textarea class="textAreaAfterSpan" name="description">').text(text);
    var $container =$(this).closest('.task-description');

    $(span).html(textarea); //add here
    $container.append($("#template").html());
});
